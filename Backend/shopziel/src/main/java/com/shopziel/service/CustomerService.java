package com.shopziel.service;

import com.shopziel.dto.CustomerDto;

public interface CustomerService {

	CustomerDto registerCustomer(CustomerDto customerDto);

	CustomerDto findByEmail(String email);
    
}
