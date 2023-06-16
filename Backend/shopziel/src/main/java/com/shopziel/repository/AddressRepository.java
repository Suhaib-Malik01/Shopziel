package com.shopziel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.Enum.AddressType;
import com.shopziel.models.Address;
import com.shopziel.models.Admin;
import com.shopziel.models.Customer;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	// Optional<Address> findByCustomerAndAddressType(Customer customer, AddressType addressType);
}
