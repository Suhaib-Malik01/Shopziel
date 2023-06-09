package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class ProductDto {

	private Integer productId;

	private String image;

	private String name;

	private String description;

	private Double price;

	private Double rating;

	private Integer categoryId;

	@JsonProperty(access = Access.READ_ONLY)
	private SellerDto seller;

	@JsonProperty(access = Access.READ_ONLY)
	private List<ReviewDto> reviews = new ArrayList<>();
}
