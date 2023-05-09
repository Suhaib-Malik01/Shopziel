package com.shopziel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    
    public Optional<Seller> findByEmail(String email);
}
