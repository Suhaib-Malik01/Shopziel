package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
    
}
