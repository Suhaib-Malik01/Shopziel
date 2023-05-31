package com.shopziel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
