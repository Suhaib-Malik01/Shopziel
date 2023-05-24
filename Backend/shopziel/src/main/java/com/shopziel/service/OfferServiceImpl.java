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

/**
 * Service class for managing offers.
 */
@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OfferRepository offerRepository;

	/**
	 * Creates a welcome offer for the specified customer.
	 *
	 * @param customer The customer for whom to create the welcome offer.
	 * @return The created welcome offer DTO.
	 */
	@Override
	public OfferDto createWelcomeOffer(Customer customer) {
		// Create a new instance of Offer
		Offer welcomeOffer = new Offer();

		// Set the customer for whom the offer is created
		welcomeOffer.setCustomer(customer);

		// Set the name of the offer
		welcomeOffer.setOfferName("WELCOME OFFER");

		// Set the expiration date of the offer (1 month from the current date)
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, 1);
		Date expirationDate = calendar.getTime();
		welcomeOffer.setOfferExpiresOn(expirationDate);

		// Set the description of the offer
		welcomeOffer.setOfferDescription("Congratulations!! Now you can avail flat 20% Off on your first order");

		// Set the status of the offer as active
		welcomeOffer.setOfferStatus(OfferStatus.OFFER_ACTIVE);

		// Save the offer in the repository
		welcomeOffer = offerRepository.save(welcomeOffer);

		// Convert the offer entity to DTO and return it
		return modelMapper.map(welcomeOffer, OfferDto.class);
	}
}
