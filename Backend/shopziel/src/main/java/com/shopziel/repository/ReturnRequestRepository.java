package com.shopziel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopziel.models.ReturnRequest;
import com.shopziel.models.Seller;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Integer> {

	List<ReturnRequest> findByOrderItemProductSeller(Seller seller);

	Page<ReturnRequest> findAll(Pageable pageable);
}
