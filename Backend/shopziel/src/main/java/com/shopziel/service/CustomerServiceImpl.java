package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.OfferDto;
import com.shopziel.dto.SellerDto;
import com.shopziel.dto.UserDto;
import com.shopziel.models.AppUser;
import com.shopziel.models.Customer;
import com.shopziel.models.Offer;
import com.shopziel.models.Seller;
import com.shopziel.repository.AppUserRepository;
import com.shopziel.repository.CustomerRepository;

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

	@Override
	public CustomerDto registerCustomer(CustomerDto customerDto) {

		Customer customer = modelMapper.map(customerDto, Customer.class);

		AppUser appUser = this.modelMapper.map(customer, AppUser.class);

		appUser = appUserRepository.save(appUser);

		OfferDto offerDto = offerService.createWelcomeOffer((Customer) appUser);

		customerDto = this.modelMapper.map(this.modelMapper.map(appUser, UserDto.class), CustomerDto.class);
		customerDto.getOffers().add(offerDto);

		return customerDto;
	}

	@Override
	public CustomerDto findByEmail(String email) {

		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));

		return this.modelMapper.map(customer, CustomerDto.class);
	}

}
