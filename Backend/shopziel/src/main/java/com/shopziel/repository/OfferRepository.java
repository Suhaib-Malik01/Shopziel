package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopziel.models.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{

	
}
