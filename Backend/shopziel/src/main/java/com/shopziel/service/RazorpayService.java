package com.shopziel.service;

import com.razorpay.*;
import com.shopziel.repository.PaymentRepository;
import com.shopziel.models.Payment;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private final String apiKey;
    private final String apiSecret;

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;

    @Autowired
    public RazorpayService(
            @Value("${razorpay.keyId}") String apiKey,
            @Value("${razorpay.keySecret}") String apiSecret,
            PaymentRepository paymentRepository) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.paymentRepository = paymentRepository;
        this.razorpayClient = initializeRazorpayClient();
    }

    private RazorpayClient initializeRazorpayClient() {
        try {
            return new RazorpayClient(apiKey, apiSecret);
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to initialize RazorpayClient", e);
        }
    }

    // Implement methods for interacting with the Razorpay API

    public Order createOrder(double amount, String currency, String receipt) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", currency);
        options.put("receipt", receipt);

        return razorpayClient.orders.create(options);
    }

    public void handlePaymentSuccess(String orderId, double amount, String currency) {
        // Create a new Payment object
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setCurrency(currency);

        // Save the Payment object to the database
        paymentRepository.save(payment);
    }
}
