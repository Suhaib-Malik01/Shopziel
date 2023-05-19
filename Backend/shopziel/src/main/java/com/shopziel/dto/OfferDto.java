package com.shopziel.dto;

import java.util.Date;

import com.shopziel.Enum.OfferStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OfferDto {
	
	private Integer offerId;
	
	private String offerName;
	
	private String offerDescription;
	
	private Date offerExpiresOn;
	
	private OfferStatus offerStatus;
	
	
}
