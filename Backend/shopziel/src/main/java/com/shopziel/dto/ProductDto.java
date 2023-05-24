package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.shopziel.models.Category;
=======
>>>>>>> 74b3de8c08c80eefcec19196e0ffafb7e4931e3b

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
