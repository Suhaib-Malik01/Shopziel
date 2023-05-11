package com.shopziel.service;

import com.shopziel.dto.AdminDto;

public interface AdminService {

	AdminDto registerAdmin(AdminDto adminDto);

	AdminDto findByEmail(String email);

}
