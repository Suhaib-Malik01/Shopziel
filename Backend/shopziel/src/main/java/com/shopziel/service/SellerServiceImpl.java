package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.dto.SellerDto;
import com.shopziel.models.Seller;
import com.shopziel.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public SellerDto registerSeller(SellerDto sellerDto) {
        

        Seller seller = modelMapper.map(sellerDto, Seller.class);

        System.out.println(seller);
       
        sellerDto.setId(sellerRepository.save(seller).getId());
    
        return sellerDto;
    }

	@Override
	public SellerDto findByEmail(String name) {
		Seller seller = sellerRepository.findByEmail(name).get();
		return this.modelMapper.map(seller, SellerDto.class);
	}
    
    
}
