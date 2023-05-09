package com.shopziel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    
    public Optional<Admin> findByEmail(String email);
    
}
