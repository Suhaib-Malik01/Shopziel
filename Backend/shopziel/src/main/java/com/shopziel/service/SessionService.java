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

/**
 * Service class for managing session-related operations.
 */
@Service
public class SessionService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SellerRepository sellerRepository;

	/**
	 * Retrieves the currently logged-in customer.
	 *
	 * @return The logged-in customer.
	 * @throws CustomerException If the customer is not found or the login has expired.
	 */

	public Customer getLoggedInCustomer() throws CustomerException {
		String customerEmail;

		// Get the authentication object from the security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			customerEmail = auth.getPrincipal().toString();
		} else {
			throw new CustomerException("Login Expired...");
		}

		// Find and return the customer by email
		return customerRepository.findByEmail(customerEmail).get();
	}

	/**
	 * Retrieves the currently logged-in seller.
	 *
	 * @return The logged-in seller.
	 * @throws SellerException If the seller is not found or the login has expired.
	 */
	public Seller getLoggedInSeller() throws SellerException {
		String sellerEmail;

		// Get the authentication object from the security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			sellerEmail = auth.getPrincipal().toString();
		} else {
			throw new SellerException("Login Expired...");
		}

		// Find and return the seller by email
		return sellerRepository.findByEmail(sellerEmail).get();
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
