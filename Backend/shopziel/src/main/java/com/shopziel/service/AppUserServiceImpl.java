package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.shopziel.Enum.Role;
import com.shopziel.dto.AdminDto;
import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.SellerDto;
import com.shopziel.dto.UserDto;
import com.shopziel.models.AppUser;
import com.shopziel.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDto registerUser(UserDto userDto) {

		userDto.setPassword(encoder.encode(userDto.getPassword()));

		if (userDto.getRole() == Role.ROLE_CUSTOMER) {
			return customerService.registerCustomer(this.modelMapper.map(userDto, CustomerDto.class));
		}
		else if (userDto.getRole() == Role.ROLE_SELLER) {
			return sellerService.registerSeller(this.modelMapper.map(userDto, SellerDto.class));
		}
		else if (userDto.getRole() == Role.ROLE_ADMIN) {
			return adminService.registerAdmin(this.modelMapper.map(userDto, AdminDto.class));
		}

		throw new NotFoundException("Something went wrong");
	}

	@Override
	public UserDto findByEmail(String email) {
		AppUser user = appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));
		return this.modelMapper.map(user, UserDto.class);
	}

}
