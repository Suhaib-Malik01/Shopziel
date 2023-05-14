package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopziel.models.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {

	private Integer productId;

	private String image;

	private String name;

	private String description;

	private Double price;

	private Double rating;

	private Category category;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private SellerDto seller;

	private List<ReviewDto> reviews = new ArrayList<>();
}
