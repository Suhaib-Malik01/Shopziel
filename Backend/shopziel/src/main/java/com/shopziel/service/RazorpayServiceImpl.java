package com.shopziel.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.shopziel.Enum.OrderStatus;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.RzpOrderDto;
import com.shopziel.exception.OrderException;
import com.shopziel.models.Customer;
import com.shopziel.models.RzpOrder;
import com.shopziel.repository.OrderRepository;
import com.shopziel.repository.RzpOrderRepository;

@Service
public class RazorpayServiceImpl implements RazorpayService {

	@Autowired
	private RazorpayClient razorpayClient;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RzpOrderRepository rzpOrderRepository;

	@Override
	public OrderDto createOrder(Integer orderId) throws RazorpayException {

		com.shopziel.models.Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order not found with order Id : " + orderId));

		if (order.getCustomer().getId() == sessionService.getLoggedInCustomer().getId()
				&& order.getStatus() == OrderStatus.PAYMENT_PENDING) {

//			if (order.getRzpOrder() != null) {
//				RzpOrder rzpOrder = rzpOrderRepository.findById(order.getRzpOrder().getId()).get();
//				order.setRzpOrder(null);
//				rzpOrderRepository.delete(rzpOrder);
//			}

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
			rzpOrderDto.setEntity(rzpOrder.get("entity"));
			rzpOrderDto.setStatus(rzpOrder.get("status"));
			rzpOrderDto.setAttempts(rzpOrder.get("attempts"));

			System.out.println(rzpOrderDto);

			order.setRzpOrder(rzpOrderRepository.save(modelMapper.map(rzpOrderDto, RzpOrder.class)));

			return modelMapper.map(orderRepository.save(order), OrderDto.class);

		}
		throw new AuthorizationServiceException("You are not authorised to pay for this order");
	}

	@Override
	public Payment capturePayment(String paymentId, int amount) throws RazorpayException {
		JSONObject paymentRequest = new JSONObject();
		paymentRequest.put("amount", amount * 100); // amount in the smallest currency unit
		Payment payment = razorpayClient.payments.fetch(paymentId);
		payment.get("");
//		 razorpayClient.payments.capture(paymentId, paymentRequest);
		System.out.println(payment);
		return payment;
	}
}
