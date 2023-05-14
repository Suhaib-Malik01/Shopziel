package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    
}
