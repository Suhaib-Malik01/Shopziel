package com.shopziel.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SELLERS_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seller extends AppUser {

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer sellerId;

	// private String email;

	// private String password;

	// private String phoneNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

}
