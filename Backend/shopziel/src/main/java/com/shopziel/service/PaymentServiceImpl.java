package com.shopziel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.models.Payment;
import com.shopziel.repository.PaymentRepository;



@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public Payment createPayment(Payment payment) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

}
