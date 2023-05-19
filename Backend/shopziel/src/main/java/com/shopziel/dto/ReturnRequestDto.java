package com.shopziel.dto;

import java.sql.Date;

import com.shopziel.Enum.ReturnRequestStatus;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;

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
