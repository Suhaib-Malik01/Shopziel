package com.shopziel.service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.OrderException;
import com.shopziel.exception.OrderItemException;
import com.shopziel.exception.ProductException;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.models.OrderItemStatus;
import com.shopziel.models.Product;
import com.shopziel.models.ReturnRequest;
import com.shopziel.models.ReturnRequestDto;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.OrderRepository;
import com.shopziel.repository.ProductRepository;
import com.shopziel.repository.ReturnRequestRepository;

public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) throws CustomerException, ProductException {

		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);

		Customer customer = sessionService.getLoggedInCustomer();

		Product product = productRepository.findById(orderItemDto.getProduct().getProductId())
				.orElseThrow(() -> new ProductException(
						"Product not found with product Id : " + orderItemDto.getProduct().getProductId()));

		orderItem.setProduct(product);
		customer.getCart().add(orderItem);

		customerRepository.save(customer);

		orderItem = orderItemRepository.save(orderItem);

		return this.modelMapper.map(orderItem, OrderItemDto.class);

	}

	@Override
	public OrderItemDto getOrderItemById(Integer orderItemId) {

		OrderItem orderItem = this.orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order Item not Found with order item id : " + orderItemId));

		return this.modelMapper.map(orderItem, OrderItemDto.class);

	}

	@Override
	public List<OrderItemDto> getOrderItemsByProductId(Integer productId) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product not found with Product Id : " + productId));

		List<OrderItem> orderItems = orderItemRepository.findByProduct(product);

		return orderItems.stream().map((item) -> this.modelMapper.map(item, OrderItemDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId) {

		List<OrderItem> orderItems = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order Not Found with Order Id : " + orderId)).getOrderItems();

		return orderItems.stream().map(item -> modelMapper.map(item, OrderItemDto.class)).collect(Collectors.toList());

	}

	@Override
	public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {

		OrderItem orderItem = orderItemRepository.findById(orderItemDto.getItemId()).orElseThrow(
				() -> new OrderItemException("Order Item Not Found with order item id : " + orderItemDto.getItemId()));

		orderItem.setQuantity(orderItem.getQuantity());

		orderItem = orderItemRepository.save(orderItem);

		return modelMapper.map(orderItem, OrderItemDto.class);
	}

	@Override
	public OrderItemDto cancelOrderItem(Integer orderItemId) {

		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order item not found with Order item id : " + orderItemId));

		if (orderItem.getStatus() == OrderItemStatus.IN_DELIVERY) {

			orderItem.setStatus(OrderItemStatus.CANCELLED);
			orderItem = orderItemRepository.save(orderItem);

			return modelMapper.map(orderItem, OrderItemDto.class);

		} else {
			throw new OrderItemException("Order item can Only be cancelled while in Delivery.");
		}

	}

	@Override
	public OrderItemStatus viewOrderItemStatus(Integer orderItemId) {

		return orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order item not found with order item id : " + orderItemId))
				.getStatus();

	}

}
