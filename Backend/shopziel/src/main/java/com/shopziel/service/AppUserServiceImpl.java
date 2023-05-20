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

/**
 * Service class for managing application users.
 */
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

	/**
	 * Registers a new user.
	 *
	 * @param userDto The DTO object containing the user details.
	 * @return The DTO object representing the registered user.
	 */
	@Override
	public UserDto registerUser(UserDto userDto) {

		// Encode the password before storing it
		userDto.setPassword(encoder.encode(userDto.getPassword()));

		if (userDto.getRole() == Role.ROLE_CUSTOMER) {
			// Register a new customer
			return customerService.registerCustomer(this.modelMapper.map(userDto, CustomerDto.class));
		} else if (userDto.getRole() == Role.ROLE_SELLER) {
			// Register a new seller
			return sellerService.registerSeller(this.modelMapper.map(userDto, SellerDto.class));
		} else if (userDto.getRole() == Role.ROLE_ADMIN) {
			// Register a new admin
			return adminService.registerAdmin(this.modelMapper.map(userDto, AdminDto.class));
		}

		throw new NotFoundException("Something went wrong");
	}

	/**
	 * Finds a user by their email.
	 *
	 * @param email The email of the user to find.
	 * @return The DTO object representing the found user.
	 * @throws UsernameNotFoundException If the user is not found.
	 */
	@Override
	public UserDto findByEmail(String email) {
		// Find the user by email in the repository
		AppUser user = appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " is not registered!!"));
		return this.modelMapper.map(user, UserDto.class);
	}
}
