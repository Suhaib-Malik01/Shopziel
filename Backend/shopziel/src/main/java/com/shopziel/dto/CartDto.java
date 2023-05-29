package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartDto {

	private List<OrderItemDto> cartItems = new ArrayList<>();

	private Double cartTotal;

	private Integer totalProducts;

}
