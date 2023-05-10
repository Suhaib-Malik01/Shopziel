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
import com.shopziel.models.Customer;
import com.shopziel.models.Seller;
import com.shopziel.repository.AdminRepository;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.SellerRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Customer> existingCustomer = customerRepository.findByEmail(username);


		if (existingCustomer.isPresent()) {

			Customer customer = existingCustomer.get();
			List<GrantedAuthority> authorities = new ArrayList<>();

			SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_CUSTOMER");
			authorities.add(sga);

			User user = new User(customer.getEmail(), customer.getPassword(), authorities);


			return user;

		}

		Optional<Seller> existingSeller = sellerRepository.findByEmail(username);

		if (existingSeller.isPresent()) {

			Seller seller = existingSeller.get();
			List<GrantedAuthority> authorities = new ArrayList<>();

			SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_SELLER");

			authorities.add(sga);
			User user = new User(seller.getEmail(), seller.getPassword(), authorities);
			

			return user;

		}

		Optional<Admin> existingAdmin = adminRepository.findByEmail(username);

		if (existingAdmin.isPresent()) {

			Admin admin = existingAdmin.get();

			List<GrantedAuthority> authorities = new ArrayList<>();
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_ADMIN");
			authorities.add(sga);

			User user = new User(admin.getEmail(), admin.getPassword(), authorities);
			

			return user;

		}

		throw new UsernameNotFoundException("User Details not found with this username: " + username);

	}

}

