package com.shopziel.service;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.shopziel.dto.OrderItemDto;

public interface CartService {

	OrderItemDto addToCart(OrderItemDto orderItemDto);
	
	OrderItemDto removeFromCart(OrderItemDto orderItemDto);
	
	OrderDto proceedToPayment();
	
	Double getCartTotal();
}
