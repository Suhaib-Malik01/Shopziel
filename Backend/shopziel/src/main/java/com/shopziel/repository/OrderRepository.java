package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
