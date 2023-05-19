package com.shopziel.service;

import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.Enum.OfferStatus;
import com.shopziel.dto.OfferDto;
import com.shopziel.models.Customer;
import com.shopziel.models.Offer;
import com.shopziel.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public OfferDto createWelcomeOffer(Customer customer) {
		Offer welcomeOffer = new Offer();
		
		welcomeOffer.setCustomer(customer);
		
		welcomeOffer.setOfferName("WELCOME OFFER");
		
		Date currentDate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, 1);
		Date expirationDate = calendar.getTime();

		welcomeOffer.setOfferExpiresOn(expirationDate);
		
		welcomeOffer.setOfferDescription("Congratulations!! Now you can avail flat 20% Off on your first order");
		
		welcomeOffer.setOfferStatus(OfferStatus.OFFER_ACTIVE);
		
		welcomeOffer = offerRepository.save(welcomeOffer);
		
		return this.modelMapper.map(welcomeOffer, OfferDto.class);
	}

}
