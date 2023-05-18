package com.shopziel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.OrderItem;
import com.shopziel.models.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByProduct(Product product);

}
