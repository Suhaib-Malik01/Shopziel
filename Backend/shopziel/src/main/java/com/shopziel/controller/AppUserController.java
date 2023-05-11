package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.AdminDto;
import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.SellerDto;
import com.shopziel.dto.UserDto;
import com.shopziel.service.AppUserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@PostMapping("/")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		
		UserDto registeredUserDto = appUserService.registerUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUserDto, HttpStatus.OK);
	}

	@GetMapping("/signIn")
	public ResponseEntity<UserDto> getLoggedInCustomerDetailsHandler(Authentication auth) {

		UserDto userDto = appUserService.findByEmail(auth.getName());

		// to get the token in body, pass HttpServletResponse inside this method
		// parameter
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		System.out.println("signIn");

		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);

	}
}
