package com.shopziel.models;

import lombok.Data;

@Data
public class RazorpayCallbackData {
	
	private String razorpay_order_id;
    private String razorpay_payment_id;
    private String razorpay_signature;
    
   
}
