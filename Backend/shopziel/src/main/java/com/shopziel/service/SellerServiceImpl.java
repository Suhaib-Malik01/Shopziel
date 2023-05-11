package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.dto.SellerDto;
import com.shopziel.models.AppUser;
import com.shopziel.models.Seller;
import com.shopziel.repository.AppUserRepository;
import com.shopziel.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SellerRepository sellerRepository;
    
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public SellerDto registerSeller(SellerDto sellerDto) {
        
        Seller seller = modelMapper.map(sellerDto, Seller.class);
        AppUser appUser = this.modelMapper.map(seller, AppUser.class);

        appUser = appUserRepository.save(appUser);
       
        sellerDto.setId(appUser.getId());
            
        return sellerDto;
    }

	@Override
	public SellerDto findByEmail(String name) {
		Seller seller = sellerRepository.findByEmail(name).get();
		return this.modelMapper.map(seller, SellerDto.class);
	}
    
    
    
    
}
