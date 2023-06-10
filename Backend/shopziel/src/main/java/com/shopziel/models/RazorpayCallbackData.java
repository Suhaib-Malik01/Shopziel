package com.shopziel.models;

import lombok.Data;

@Data
public class RazorpayCallbackData {
	private String orderId;
    private double amount;
    private String currency;

}
