package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.shopziel.dto.OrderDto;
import com.shopziel.models.RazorpayCallbackData;
import com.shopziel.service.RazorpayService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private RazorpayService razorpayService;

    @Autowired
    public PaymentController(RazorpayService razorpayService) {
        this.razorpayService = razorpayService;
    }

    @PostMapping("/razorpay/{orderId}")
    public ResponseEntity<OrderDto> createPayment(@PathVariable Integer orderId) throws RazorpayException {
           
    	// Process the order and return the response
          return ResponseEntity.ok(razorpayService.createRzpOrder(orderId));
       
    }

    @PostMapping("/razorpay/callback/{orderId}")
    public ResponseEntity<OrderDto> handleRazorpayCallback(@RequestBody RazorpayCallbackData callbackData, @PathVariable Integer orderId) {
         // Pass the relevant data to the RazorpayService
         // Return the successfull order 
        return ResponseEntity.ok(razorpayService.handlePaymentSuccess(callbackData,orderId));
    }
    
}
