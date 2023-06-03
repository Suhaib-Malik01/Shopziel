package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.OfferDto;
import com.shopziel.models.Customer;

public interface OfferService {

	OfferDto createWelcomeOffer(Customer customer);

	public List<OfferDto> getAllOffers();
}
