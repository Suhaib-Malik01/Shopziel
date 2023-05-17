package com.shopziel.dto;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

	private Integer itemId;

	private OrderDto order;

	private ProductDto product;

	private Integer quantity;

	private Double price;
}
