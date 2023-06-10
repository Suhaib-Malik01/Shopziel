package com.shopziel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopziel.models.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
