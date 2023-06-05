package com.shopziel.service;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.RzpOrderDto;

public interface RazorpayService {

	OrderDto createOrder(Integer orderId) throws RazorpayException;

	Payment capturePayment(String paymentId, int amount) throws RazorpayException;
}
