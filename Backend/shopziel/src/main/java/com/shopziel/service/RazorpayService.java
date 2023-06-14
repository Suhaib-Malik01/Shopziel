package com.shopziel.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.shopziel.repository.OrderRepository;
import com.shopziel.repository.PaymentRepository;
import com.shopziel.repository.RzpOrderRepository;
import com.shopziel.Enum.OrderStatus;

import com.shopziel.dto.OrderDto;

import com.shopziel.models.Payment;
import com.shopziel.models.RazorpayCallbackData;
import com.shopziel.models.RzpOrder;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.OrderException;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
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
    private SessionService sessionService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RzpOrderRepository rzpOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public OrderDto createRzpOrder(Integer orderId) throws RazorpayException {

        com.shopziel.models.Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found with order Id : " + orderId));

        if (order.getCustomer().getId() != sessionService.getLoggedInCustomer().getId())
            throw new CustomerException("You are not authorized to create order with ID: " + orderId);

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", order.getTotalBillAmount() * 100); // amount in the smallest currency unit
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "Order_Id_" + orderId + "_of_Customer_Id_" + order.getCustomer().getId());

        Order rzpOrder = razorpayClient.orders.create(orderRequest);
        System.out.println(rzpOrder);

        RzpOrderDto rzpOrderDto = new RzpOrderDto();

        rzpOrderDto.setId(rzpOrder.get("id"));
        rzpOrderDto.setAmount(rzpOrder.get("amount"));
        rzpOrderDto.setAmountPaid(rzpOrder.get("amount_paid"));
        rzpOrderDto.setCreatedAt(rzpOrder.get("created_at"));
        rzpOrderDto.setAmountDue(rzpOrder.get("amount_due"));
        rzpOrderDto.setCurrency(rzpOrder.get("currency"));
        rzpOrderDto.setReceipt(rzpOrder.get("receipt"));
        rzpOrderDto.setStatus(rzpOrder.get("status"));

        System.out.println(rzpOrderDto);

        order.setRzpOrder(rzpOrderRepository.save(modelMapper.map(rzpOrderDto, RzpOrder.class)));

        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    public OrderDto handlePaymentSuccess(RazorpayCallbackData callbackData, Integer orderId) {

        // Create a new Payment object
        Payment payment = new Payment();
        payment.setRazorpay_orderId(callbackData.getRazorpay_order_id());
        payment.setRazorpay_payment_id(callbackData.getRazorpay_payment_id());
        payment.setRazorpay_signature(callbackData.getRazorpay_signature());

        com.shopziel.models.Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found with order Id : " + orderId));

        if (order.getCustomer().getId() != sessionService.getLoggedInCustomer().getId())
            throw new CustomerException("You are not authorized to create order with ID: " + orderId);

        order.setPayment(paymentRepository.save(payment));
        order.setStatus(OrderStatus.ORDER_CONFIRMED);

        // Save the order success to the database
        
        

        return modelMapper.map(orderRepository.save(order), OrderDto.class);

    }

}
