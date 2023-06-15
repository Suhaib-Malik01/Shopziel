package com.shopziel.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMERS_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends AppUser {

	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@ElementCollection
	private List<Product> wishlist = new ArrayList<>();

	@ElementCollection
	private Set<OrderItem> cart = new HashSet<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Offer> offers = new ArrayList<>();
}
