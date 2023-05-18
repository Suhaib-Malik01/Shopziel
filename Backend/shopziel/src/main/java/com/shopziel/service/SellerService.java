package com.shopziel.service;

import com.shopziel.dto.SellerDto;

public interface SellerService {

    public SellerDto registerSeller(SellerDto sellerDto);

	public SellerDto findByEmail(String name);
    
	// getProductsBySeller(Integer sellerId); //fetch from orderItems table 
}
