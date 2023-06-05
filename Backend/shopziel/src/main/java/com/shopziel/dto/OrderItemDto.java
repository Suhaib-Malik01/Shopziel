package com.shopziel.dto;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.shopziel.Enum.OrderItemStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Integer itemId;

	@JsonProperty(access = Access.READ_ONLY)
	private OrderDto order;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer productId;

	@JsonProperty(access = Access.READ_ONLY)
	private ProductDto product;

	private Integer quantity;

	private OrderItemStatus status;

	@JsonProperty(access = Access.READ_ONLY)
	private Double price;
}
