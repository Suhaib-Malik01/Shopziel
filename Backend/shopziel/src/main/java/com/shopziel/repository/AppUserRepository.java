package com.shopziel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    
    public Optional<AppUser> findByEmail(String email);
    
}
