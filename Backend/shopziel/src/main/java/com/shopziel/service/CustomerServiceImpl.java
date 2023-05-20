package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.OfferDto;
import com.shopziel.dto.UserDto;
import com.shopziel.models.AppUser;
import com.shopziel.models.Customer;
import com.shopziel.repository.AppUserRepository;
import com.shopziel.repository.CustomerRepository;

/**
 * Service class for managing customer-related operations.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OfferService offerService;

	/**
	 * Registers a new customer.
	 *
	 * @param customerDto The DTO object containing customer details.
	 * @return The DTO object representing the registered customer.
	 */
	@Override
	public CustomerDto registerCustomer(CustomerDto customerDto) {

		// Map the customerDto to a Customer entity
		Customer customer = modelMapper.map(customerDto, Customer.class);

		// Map the customer to an AppUser entity
		AppUser appUser = this.modelMapper.map(customer, AppUser.class);

		// Save the appUser in the repository
		appUser = appUserRepository.save(appUser);

		// Create a welcome offer for the customer
		OfferDto offerDto = offerService.createWelcomeOffer((Customer) appUser);

		// Map the appUser to a UserDto and then to a CustomerDto
		customerDto = this.modelMapper.map(this.modelMapper.map(appUser, UserDto.class), CustomerDto.class);

		// Add the welcome offer to the customerDto
		customerDto.getOffers().add(offerDto);

		// Return the registered customerDto
		return customerDto;
	}

	/**
	 * Finds a customer by email.
	 *
	 * @param email The email of the customer to find.
	 * @return The DTO object representing the found customer.
	 * @throws UsernameNotFoundException If a customer with the specified email is not found.
	 */
	@Override
	public CustomerDto findByEmail(String email) {

		// Find the customer by email in the repository
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));

		// Map the customer to a CustomerDto and return it
		return this.modelMapper.map(customer, CustomerDto.class);
	}
}
