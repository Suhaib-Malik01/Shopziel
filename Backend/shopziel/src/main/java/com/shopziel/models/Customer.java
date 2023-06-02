package com.shopziel.models;

import java.util.ArrayList;
import java.util.List;

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

	@ManyToMany
	@JoinTable(name = "join_table_name", joinColumns = @JoinColumn(name = "entity_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private List<Address> addresses;

	@ElementCollection
	private List<Product> wishlist = new ArrayList<>();

	@ElementCollection
	private List<OrderItem> cart = new ArrayList<>();

	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Offer> offers = new ArrayList<>();
}
