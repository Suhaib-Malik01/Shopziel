package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.RzpOrderDto;
import com.shopziel.service.CartService;
import com.shopziel.service.RazorpayService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private RazorpayService razorpayService;

	@Autowired
	private CartService cartService;

	@PostMapping("/create-order/")
	public ResponseEntity<OrderDto> createOrder(@RequestParam("orderId") Integer orderId) throws RazorpayException {

		return ResponseEntity.ok(razorpayService.createOrder(orderId));
	}

	@PostMapping("/capture-payment")
	public ResponseEntity<Payment> capturePayment(@RequestParam String paymentId, @RequestParam int amount)
			throws RazorpayException {
		Payment payment = razorpayService.capturePayment(paymentId, amount);
		return ResponseEntity.ok(payment);
	}

	@GetMapping("/proceedToPayment")
	public ResponseEntity<OrderDto> proceedToPayment() throws RazorpayException {
		return new ResponseEntity<OrderDto>(cartService.proceedToPayment(), HttpStatus.OK);
	}

}
