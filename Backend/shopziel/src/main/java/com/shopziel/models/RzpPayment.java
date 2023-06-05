package com.shopziel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RzpPayment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

}
