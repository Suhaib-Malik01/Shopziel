package com.shopziel.dto;

import com.shopziel.models.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {

	private Integer id;

	private String review;

	private String imageUrl;

	private Double rating;

	private CustomerDto customer;

}
