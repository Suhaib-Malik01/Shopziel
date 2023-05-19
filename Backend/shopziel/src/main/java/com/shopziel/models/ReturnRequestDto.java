package com.shopziel.models;

import java.sql.Date;

import lombok.Data;

@Data
public class ReturnRequestDto {
	
	private Integer returnRequestId;

	private Customer customer;

	private OrderItem orderItem;

	private String reason;

	private Date requestRaisedDate;

	private ReturnRequestStatus status;

	private Date requestResloveDate;
}
