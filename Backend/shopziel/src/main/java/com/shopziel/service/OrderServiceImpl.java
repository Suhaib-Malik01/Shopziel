package com.shopziel.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		
	}

	@Override
	public List<OrderDto> getAllOrdersOfCustomer(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto updateOrder(Integer orderId, OrderDto updatedOrderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto cancelOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
