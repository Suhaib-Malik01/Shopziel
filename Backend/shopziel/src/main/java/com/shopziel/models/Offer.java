package com.shopziel.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OFFERS_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer offerId;

	private String offerName;

	private String offerDescription;

	private Date offerExpiresOn;
	
	@Enumerated(EnumType.STRING)
	private OfferStatus offerStatus;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
