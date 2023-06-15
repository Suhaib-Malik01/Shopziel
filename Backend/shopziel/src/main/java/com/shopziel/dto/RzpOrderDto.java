package com.shopziel.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class RzpOrderDto {
	private String id;
	private Integer amount;
	private Integer amountPaid;
	private Date createdAt;
	private Integer amountDue;
	private String currency;
	private String receipt;
	private String entity;
	private String status;
	private Integer attempts;

}
