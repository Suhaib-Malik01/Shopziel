package com.shopziel.service;

import java.util.Date;

import lombok.Data;

@Data
public class RzpOrderDto {

	
	private String id;
	private Integer amount;
	private Integer amountPaid;
	private Integer amountDue;
	private String currency;
	private String receipt;
	private String status;
	private Date createdAt;
	
}
