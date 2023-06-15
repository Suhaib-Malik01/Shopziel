package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.AddressDto;
import com.shopziel.dto.CustomerDto;

public interface CustomerService {

	CustomerDto registerCustomer(CustomerDto customerDto);

	CustomerDto findByEmail(String email);

	public CustomerDto getCustomer();

	CustomerDto addAddress(AddressDto addressDto);

	public List<AddressDto> getAddress();
}
