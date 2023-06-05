package com.shopziel.service;


import com.razorpay.RazorpayException;
import com.shopziel.dto.CartDto;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.RzpOrderDto;

public interface CartService {

	OrderItemDto addToCart(OrderItemDto orderItemDto);

	OrderItemDto removeFromCart(Integer orderItemId);

	OrderDto proceedToPayment() throws RazorpayException;

	Double getCartTotal();

	CartDto getOrderItemsOfCart();

	OrderItemDto updateQuantity(int orderItemId, int quantity);
}
