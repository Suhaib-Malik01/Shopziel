package com.shopziel.dto;

import com.shopziel.Enum.AddressType;

import lombok.Data;

@Data
public class AddressDto {
	private Integer addressId;

	private String address;

	private String street;

	private String city;

	private String state;

	private String country;

	private Integer postalCode;

	private AddressType addressType;
}
