package com.shopziel.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

public interface OrderService {

	OrderDto createOrder(OrderDto orderDto);

	List<OrderDto> getAllOrdersOfCustomer(Integer customerId);

	OrderDto getOrderById(Integer orderId);

	OrderDto updateOrder(Integer orderId, OrderDto updatedOrderDto);

	OrderDto cancelOrder(Integer orderId);

}
