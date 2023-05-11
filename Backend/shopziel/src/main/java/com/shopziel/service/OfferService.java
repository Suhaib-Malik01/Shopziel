package com.shopziel.service;

import com.shopziel.dto.OfferDto;
import com.shopziel.models.Customer;

public interface OfferService {

	OfferDto createWelcomeOffer(Customer customer);
}
