package com.shopziel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.OrderItem;
import com.shopziel.models.Product;
import com.shopziel.models.Seller;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByProduct(Product product);

	List<OrderItem> findByProductSeller(Seller seller);

	Page<OrderItem> findAllByOrderOrderByOrderDate(Pageable pageable);

}
