package com.shopziel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.OrderException;
import com.shopziel.exception.OrderItemException;
import com.shopziel.exception.ProductException;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.models.Product;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.OrderRepository;
import com.shopziel.repository.ProductRepository;

/**
 * The implementation of the OrderItemService interface.
 * Handles the logic for managing order items.
 */
@Service
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
		// Map the OrderItemDto to OrderItem entity
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);

		// Get the logged-in customer
		Customer customer = sessionService.getLoggedInCustomer();

		// Find the product by its ID
		Product product = productRepository.findById(orderItemDto.getProduct().getProductId())
				.orElseThrow(() -> new ProductException(
						"Product not found with product Id : " + orderItemDto.getProduct().getProductId()));

		// Set the product and add the order item to the customer's cart
		orderItem.setProduct(product);
		customer.getCart().add(orderItem);

		// Save the updated customer
		customerRepository.save(customer);

		// Save the order item
		orderItem = orderItemRepository.save(orderItem);

		// Map the saved order item back to OrderItemDto and return
		return this.modelMapper.map(orderItem, OrderItemDto.class);
	}

	@Override
	public OrderItemDto getOrderItemById(Integer orderItemId) {
		// Find the order item by its ID
		OrderItem orderItem = this.orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order Item not Found with order item id : " + orderItemId));

		// Map the order item to OrderItemDto and return
		return this.modelMapper.map(orderItem, OrderItemDto.class);
	}

	@Override
	public List<OrderItemDto> getOrderItemsByProductId(Integer productId) {
		// Find the product by its ID
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product not found with Product Id : " + productId));

		// Find all order items associated with the product
		List<OrderItem> orderItems = orderItemRepository.findByProduct(product);

		// Map the order items to OrderItemDto and return as a list
		return orderItems.stream().map((item) -> this.modelMapper.map(item, OrderItemDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId) {
		// Find the order by its ID
		List<OrderItem> orderItems = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order Not Found with Order Id : " + orderId)).getOrderItems();

		// Map the order items to OrderItemDto and return as a list
		return orderItems.stream().map(item -> modelMapper.map(item, OrderItemDto.class)).collect(Collectors.toList());
	}

	@Override
	public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {
		// Find the order item by its ID
		OrderItem orderItem = orderItemRepository.findById(orderItemDto.getItemId()).orElseThrow(
				() -> new OrderItemException("Order Item Not Found with order item id : " + orderItemDto.getItemId()));

		// Update the order item's quantity
		orderItem.setQuantity(orderItem.getQuantity());

		// Save the updated order item
		orderItem = orderItemRepository.save(orderItem);

		// Map the updated order item to OrderItemDto and return
		return modelMapper.map(orderItem, OrderItemDto.class);
	}

	@Override
	public OrderItemDto cancelOrderItem(Integer orderItemId) {
		// Find the order item by its ID
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order item not found with Order item id : " + orderItemId));

		// Check if the order item is in delivery status
		if (orderItem.getStatus() == OrderItemStatus.IN_DELIVERY) {
			// Update the order item status to cancelled
			orderItem.setStatus(OrderItemStatus.CANCELLED);
			orderItem = orderItemRepository.save(orderItem);

			// Map the cancelled order item to OrderItemDto and return
			return modelMapper.map(orderItem, OrderItemDto.class);
		} else {
			throw new OrderItemException("Order item can Only be cancelled while in Delivery.");
		}
	}

	@Override
	public OrderItemStatus viewOrderItemStatus(Integer orderItemId) {
		// Find the order item by its ID and return its status
		return orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Order item not found with order item id : " + orderItemId))
				.getStatus();
	}

	@Override
	public Page<OrderItemDto> getAllOrderItems(Integer pageSize, String sortDirection, Integer pageNo) {
		// Set the sort direction based on the input parameter
		Sort.Direction direction = Sort.Direction.ASC;
		if (sortDirection.equalsIgnoreCase("desc")) {
			direction = Sort.Direction.DESC;
		}

		// Create pageable object for pagination
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, "order.orderDate"));

		// Fetch the order items page from the repository
		Page<OrderItem> orderItemsPage = orderItemRepository.findAllByOrderOrderByOrderDate(pageable);

		// Map the order items page to OrderItemDto and return as a new page
		List<OrderItemDto> orderItemDtoList = orderItemsPage.stream()
				.map(item -> modelMapper.map(item, OrderItemDto.class)).collect(Collectors.toList());
		
		return new PageImpl<>(orderItemDtoList, pageable, orderItemsPage.getTotalElements());
	}
}
