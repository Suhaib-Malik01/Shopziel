package com.shopziel.service;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.SellerDto;

public interface CustomerService {

	CustomerDto registerCustomer(CustomerDto customerDto);

	CustomerDto findByEmail(String email);
    
}
