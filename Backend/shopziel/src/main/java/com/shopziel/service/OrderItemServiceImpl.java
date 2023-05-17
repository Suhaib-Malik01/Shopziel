package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.ProductDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.repository.CustomerRepository;

public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	private 
	
	@Override
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) throws CustomerException {
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);
		Customer customer = customerService.getLoggedInCustomer();
		customer.getCart().add(orderItem);
		customerRepository.save(customer);
		
		
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
