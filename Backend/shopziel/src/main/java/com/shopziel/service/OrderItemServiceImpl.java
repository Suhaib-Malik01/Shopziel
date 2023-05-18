package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.models.Product;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.ProductRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto getOrderItemByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto getAllOrderItemsByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto removeOrderItemFromOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
