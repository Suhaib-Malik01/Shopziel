package com.shopziel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.Enum.OrderStatus;
import com.shopziel.models.Customer;
import com.shopziel.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByCustomer(Customer customer);

	List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);

}
