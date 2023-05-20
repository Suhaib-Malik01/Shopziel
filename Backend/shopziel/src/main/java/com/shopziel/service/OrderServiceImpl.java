package com.shopziel.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.Enum.OrderStatus;
import com.shopziel.dto.OrderDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.OrderException;
import com.shopziel.models.Customer;
import com.shopziel.models.Order;
import com.shopziel.models.OrderItem;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.OrderRepository;

/**
 * Service implementation for managing orders.
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public List<OrderDto> getAllOrdersOfCustomer(Integer customerId) {
		// Find the customer by ID
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerException("Customer not found with customer Id : " + customerId));

		// Find all orders associated with the customer
		List<Order> orders = orderRepository.findByCustomer(customer);

		// Map the orders to OrderDto and return as a list
		return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public OrderDto getOrderById(Integer orderId) {
		// Find the order by ID
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order not found with order Id : " + orderId));

		// Map the order to OrderDto and return
		return modelMapper.map(order, OrderDto.class);
	}

	@Override
	public OrderDto cancelOrder(Integer orderId) {
		// Get the logged-in customer
		Customer customer = sessionService.getLoggedInCustomer();

		// Find the order by ID
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order not found with order id : " + orderId));

		// Check if the logged-in customer is the owner of the order
		if (customer.getId() == order.getCustomer().getId()) {
			// Update the order status to cancelled
			order.setStatus(OrderStatus.ORDER_CANCELED);

			// Update the status of all order items in the order to cancelled
			order.getOrderItems().forEach((item) -> {
				item.setStatus(OrderItemStatus.CANCELLED);
				orderItemRepository.save(item);
			});

			// Save the updated order
			order = orderRepository.save(order);
		}

		// Map the cancelled order to OrderDto and return
		return modelMapper.map(order, OrderDto.class);
	}

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		// Get the logged-in customer
		Customer customer = sessionService.getLoggedInCustomer();

		// Map the OrderDto to Order entity
		Order order = modelMapper.map(orderDto, Order.class);
		order.setOrderDate(Date.valueOf(LocalDate.now()));

		Double totalBillAmount = 0.00;

		// Process each order item in the customer's cart
		for (OrderItem item : customer.getCart()) {
			item.setStatus(OrderItemStatus.PAYMENT_PENDING);
			item.setOrder(order);
			item.setDeliveryDate(Date.valueOf(LocalDate.now().plusDays(7)));

			totalBillAmount += (item.getPrice() * item.getQuantity());

			// Save the order item and add it to orderItemList in order
			order.getOrderItems().add(orderItemRepository.save(item));
		}

		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setTotalBillAmount(totalBillAmount);
		order.setCustomer(customer);

		// Save the order
		order = orderRepository.save(order);

		// Map the created order to OrderDto and return
		return modelMapper.map(order, OrderDto.class);
	}

	@Override
	public List<OrderDto> getAllOrdersOfLoggedInCustomer() {
		// Get the logged-in customer
		Customer customer = sessionService.getLoggedInCustomer();

		// Find all orders associated with the customer
		return orderRepository.findByCustomer(customer).stream()
				.map((order) -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	}
}
