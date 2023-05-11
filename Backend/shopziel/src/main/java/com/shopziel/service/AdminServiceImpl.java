package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopziel.dto.AdminDto;
import com.shopziel.dto.UserDto;
import com.shopziel.models.Admin;
import com.shopziel.models.AppUser;
import com.shopziel.repository.AdminRepository;
import com.shopziel.repository.AppUserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminDto registerAdmin(AdminDto adminDto) {

		Admin admin = modelMapper.map(adminDto, Admin.class);
		AppUser appUser = this.modelMapper.map(admin, AppUser.class);

		appUser = appUserRepository.save(appUser);

		return (AdminDto) this.modelMapper.map(appUser, UserDto.class);

	}

	@Override
	public AdminDto findByEmail(String email) {

		Admin admin = adminRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));

		return this.modelMapper.map(admin, AdminDto.class);
	}

}
