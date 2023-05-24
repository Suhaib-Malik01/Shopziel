package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.OrderDto;


public interface OrderService {

	OrderDto createOrder(OrderDto orderDto);

	List<OrderDto> getAllOrdersOfCustomer(Integer customerId);

	List<OrderDto> getAllOrdersOfLoggedInCustomer();

	OrderDto getOrderById(Integer orderId);

	OrderDto cancelOrder(Integer orderId);

}
