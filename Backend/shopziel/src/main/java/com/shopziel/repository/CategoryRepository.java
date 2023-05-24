package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopziel.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    
}
