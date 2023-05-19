package com.shopziel.service;

import com.shopziel.dto.SellerDto;
import com.shopziel.exception.SellerException;

public interface SellerService {

    public SellerDto registerSeller(SellerDto sellerDto);

    public SellerDto updateSeller(SellerDto sellerDto) throws SellerException;

    public SellerDto deleteSeller() throws SellerException;

	public SellerDto findByEmail(String name);
}
