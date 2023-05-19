package com.shopziel.service;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shopziel.exception.CustomerException;
import com.shopziel.exception.SellerException;
import com.shopziel.models.Customer;
import com.shopziel.models.Seller;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.SellerRepository;

@Service
public class SessionService {
	
	@Autowired
	private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;
	
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

    public Seller getLoggedInSeller() throws SellerException {
		String sellerEmail;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
        	sellerEmail = auth.getPrincipal().toString();
        } else {
            throw new CustomerException("Login Expired...");
        }
        
       return sellerRepository.findByEmail(sellerEmail).get();
	}
}
