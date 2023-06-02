package com.shopziel.models;

import java.sql.Date;

import com.shopziel.Enum.OrderItemStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_ITEMS_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;

	private Double price;

	@Enumerated(EnumType.STRING)
	private OrderItemStatus status;

	private Date deliveryDate;

}
