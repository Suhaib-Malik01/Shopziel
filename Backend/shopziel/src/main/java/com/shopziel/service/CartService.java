package com.shopziel.service;


import com.shopziel.dto.CartDto;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.OrderItemDto;

public interface CartService {

	OrderItemDto addToCart(OrderItemDto orderItemDto);

	OrderItemDto removeFromCart(Integer orderItemId);

	OrderDto proceedToPayment();

	Double getCartTotal();

	CartDto getOrderItemsOfCart();
}
