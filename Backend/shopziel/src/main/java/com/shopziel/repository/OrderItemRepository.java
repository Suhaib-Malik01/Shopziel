package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
