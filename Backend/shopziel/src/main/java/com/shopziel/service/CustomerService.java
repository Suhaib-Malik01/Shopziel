package com.shopziel.service;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.SellerDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.models.Customer;

public interface CustomerService {

	CustomerDto registerCustomer(CustomerDto customerDto);

	CustomerDto findByEmail(String email);
    
}
