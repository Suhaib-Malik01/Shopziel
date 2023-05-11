package com.shopziel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopziel.models.Admin;
import com.shopziel.models.AppUser;
import com.shopziel.models.Customer;
import com.shopziel.models.Seller;
import com.shopziel.repository.AdminRepository;
import com.shopziel.repository.AppUserRepository;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.SellerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<AppUser> existingappUser = appUserRepository.findByEmail(username);

		if (existingappUser.isPresent()) {

			AppUser appUser = existingappUser.get();
			List<GrantedAuthority> authorities = new ArrayList<>();

			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(appUser.getRole());
			authorities.add(sga);

			User user = new User(appUser.getEmail(), appUser.getPassword(), authorities);

			return user;

		}

		throw new UsernameNotFoundException("User Details not found with this username: " + username);

	}

}
