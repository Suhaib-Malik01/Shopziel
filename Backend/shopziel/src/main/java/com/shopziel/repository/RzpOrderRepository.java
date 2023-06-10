package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopziel.models.RzpOrder;

@Repository
public interface RzpOrderRepository extends JpaRepository<RzpOrder, Integer> {

}
