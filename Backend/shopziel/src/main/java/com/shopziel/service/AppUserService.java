package com.shopziel.service;

import com.shopziel.dto.UserDto;

public interface AppUserService {

	public UserDto registerUser(UserDto userDto);

	public UserDto findByEmail(String email);

}
