package com.shopziel.controller;

import com.shopziel.service.OrderService;

import jakarta.validation.Valid;

import com.shopziel.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * The OrderController class handles the API endpoints related to orders.
 * 
 * It provides operations to retrieve and manipulate order data.
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 
	 * Retrieves all orders of a specific customer.
	 * 
	 * @param customerId The ID of the customer.
	 * 
	 * @return ResponseEntity containing a list of OrderDto objects representing the
	 *         customer's orders.
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<OrderDto>> getAllOrdersOfCustomer(@PathVariable Integer customerId) {

		List<OrderDto> orders = orderService.getAllOrdersOfCustomer(customerId);

		return ResponseEntity.ok(orders);
	}

	/**
	 * 
	 * Retrieves an order by its ID.
	 * 
	 * @param orderId The ID of the order.
	 * 
	 * @return ResponseEntity containing the OrderDto object representing the order.
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {

		OrderDto order = orderService.getOrderById(orderId);

		return ResponseEntity.ok(order);
	}

	/**
	 * 
	 * Cancels an order.
	 * 
	 * @param orderId The ID of the order to cancel.
	 * 
	 * @return ResponseEntity containing the OrderDto object representing the
	 *         cancelled order.
	 */
	@PostMapping("/cancel/{orderId}")
	public ResponseEntity<OrderDto> cancelOrder(@PathVariable Integer orderId) {

		OrderDto cancelledOrder = orderService.cancelOrder(orderId);

		return ResponseEntity.ok(cancelledOrder);
	}

	/**
	 * 
	 * Creates a new order.
	 * 
	 * @param orderDto The OrderDto object containing the order data.
	 * 
	 * @return ResponseEntity containing the OrderDto object representing the
	 *         created order.
	 */
	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {

		OrderDto createdOrder = orderService.createOrder(orderDto);

		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	/**
	 * 
	 * Retrieves all orders of the currently logged-in customer.
	 * 
	 * @return ResponseEntity containing a list of OrderDto objects representing the
	 *         logged-in customer's orders.
	 */
	@GetMapping("/loggedin")
	public ResponseEntity<List<OrderDto>> getAllOrdersOfLoggedInCustomer() {

		List<OrderDto> orders = orderService.getAllOrdersOfLoggedInCustomer();

		return ResponseEntity.ok(orders);
	}
}