package com.shopziel.models;

import java.sql.Date;

import com.shopziel.Enum.ReturnRequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ReturnRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer returnRequestId;

	@ManyToOne
	private Customer customer;

	@OneToOne
	private OrderItem orderItem;

	private String reason;

	private Date requestRaisedDate;

	@Enumerated(EnumType.STRING)
	private ReturnRequestStatus status;

	private Date requestResloveDate;
}
