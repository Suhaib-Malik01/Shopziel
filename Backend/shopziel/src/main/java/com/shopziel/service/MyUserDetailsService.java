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

import com.shopziel.models.AppUser;
import com.shopziel.repository.AppUserRepository;

/**
 * Service class for loading user details for authentication and authorization.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;

	/**
	 * Loads the user details by the given username.
	 *
	 * @param username The username to load user details for.
	 * @return The UserDetails object containing the user details.
	 * @throws UsernameNotFoundException If the user details are not found for the given username.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Find the user with the given username in the repository
		Optional<AppUser> existingAppUser = appUserRepository.findByEmail(username);

		if (existingAppUser.isPresent()) {
			// Retrieve the AppUser object from Optional
			AppUser appUser = existingAppUser.get();

			// Create a list of GrantedAuthority objects for user roles
			List<GrantedAuthority> authorities = new ArrayList<>();
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(appUser.getRole().toString());
			authorities.add(sga);

			// Create a UserDetails object representing the user
			User user = new User(appUser.getEmail(), appUser.getPassword(), authorities);

			return user;
			// Return the UserDetails object
		}

		throw new UsernameNotFoundException("User Details not found with this username: " + username);
		// Throw an exception if user details are not found
	}
}
