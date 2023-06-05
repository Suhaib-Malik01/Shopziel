package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.OfferDto;

public interface CustomerService {

	CustomerDto registerCustomer(CustomerDto customerDto);

	CustomerDto findByEmail(String email);

	public CustomerDto getCustomer();
    
}
