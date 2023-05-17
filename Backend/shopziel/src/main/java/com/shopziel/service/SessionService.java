package com.shopziel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shopziel.exception.CustomerException;
import com.shopziel.models.Customer;
import com.shopziel.repository.CustomerRepository;

@Service
public class SessionService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getLoggedInCustomer() throws CustomerException {
		String customerEmail;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
        	customerEmail = auth.getPrincipal().toString();
        } else {
            throw new CustomerException("Login Expired...");
        }
        
       return customerRepository.findByEmail(customerEmail).get();
	}
}
