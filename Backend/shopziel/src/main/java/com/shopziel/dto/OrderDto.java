package com.shopziel.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.shopziel.Enum.OrderStatus;
import com.shopziel.models.Address;

import com.shopziel.models.Payment;
import com.shopziel.models.RzpOrder;

import lombok.Data;

@Data
public class OrderDto {

	private Integer orderId;

	@JsonProperty(access = Access.READ_ONLY)
	private Date orderDate;

	@JsonProperty(access = Access.READ_ONLY)
	private OrderStatus status;

	@JsonProperty(access = Access.READ_ONLY)
	private CustomerDto customer;

	@JsonProperty(access = Access.READ_ONLY)
	private List<OrderItemDto> orderItems = new ArrayList<>();

	@JsonProperty(access = Access.READ_WRITE)
	private Address billingAddress;

	@JsonProperty(access = Access.READ_WRITE)
	private Address deliveryAddress;

	@JsonProperty(access = Access.READ_ONLY)
	private Double totalBillAmount;
	
	@JsonProperty(access = Access.READ_ONLY)
	private RzpOrder rzpOrder;

	@JsonProperty(access = Access.READ_ONLY)
	private Payment payment;
}
