package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	private AppUserRepository appUserRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public SellerDto registerSeller(SellerDto sellerDto) {

		Seller seller = modelMapper.map(sellerDto, Seller.class);
		AppUser appUser = this.modelMapper.map(seller, AppUser.class);

		appUser = appUserRepository.save(appUser);

		return this.modelMapper.map(appUser, SellerDto.class);
	}


	@Override
	public SellerDto findByEmail(String email) {
		Seller seller = sellerRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));
		return this.modelMapper.map(seller, SellerDto.class);
	}


	@Override
	public SellerDto updateSeller(SellerDto sellerDto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateSeller'");
	}


	@Override
	public SellerDto deleteSeller() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteSeller'");
	}

}
