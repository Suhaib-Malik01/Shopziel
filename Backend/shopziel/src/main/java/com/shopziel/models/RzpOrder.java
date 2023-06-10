package com.shopziel.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RzpOrder {
	@Id
	private String id;
	private Integer amount;
	private Integer amountPaid;
	private Integer amountDue;
	private String currency;
	private String receipt;
	private String status;
	private Date createdAt;

}