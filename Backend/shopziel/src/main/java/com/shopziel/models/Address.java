package com.shopziel.models;

import com.shopziel.Enum.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESSES_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;

    private String address;

    private String street;

    private String city;

    private String state;

    private String country;

    private Integer postalCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
