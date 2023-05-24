package com.shopziel.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.shopziel.Enum.OrderStatus;
import com.shopziel.models.Address;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;

import lombok.Data;

@Data
public class OrderDto {
	private Integer orderId;

	private Date orderDate;

	private OrderStatus status;

	private CustomerDto customer;

	private List<OrderItemDto> orderItems = new ArrayList<>();

	private Address billingAddress;

	private Address deliveryAddress;

	private Double totalBillAmount;
}
