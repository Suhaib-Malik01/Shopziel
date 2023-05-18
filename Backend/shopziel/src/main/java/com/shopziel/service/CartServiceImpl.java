package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;

public class CartServiceImpl implements CartService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItemDto addToCart(OrderItemDto orderItemDto) {

		Customer customer = this.sessionService.getLoggedInCustomer();
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);

		if (!customer.getCart().contains(orderItem)) {
			customer.getCart().add(orderItem);
		} else {
			int i = customer.getCart().indexOf(orderItem);
			customer.getCart().get(i).setQuantity(customer.getCart().get(i).getQuantity() + 1);
		}

		customerRepository.save(customer);
		orderItem = orderItemRepository.save(orderItem);

		return this.modelMapper.map(orderItem, OrderItemDto.class);

	}

	@Override
	public OrderItemDto removeFromCart(OrderItemDto orderItemDto) {

		Customer customer = this.sessionService.getLoggedInCustomer();
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);

		if (customer.getCart().contains(orderItem)) {
			customer.getCart().remove(orderItem);
		}
		customerRepository.save(customer);
		orderItemRepository.delete(orderItem);

		return orderItemDto;
	}

	@Override
	public OrderDto proceedToPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getCartTotal() {

		Customer customer = this.sessionService.getLoggedInCustomer();
		Double cartTotal = 0.00;

		for (OrderItem orderItem : customer.getCart()) {
			cartTotal += (orderItem.getPrice() * orderItem.getQuantity());
		}

		return cartTotal;
	}

}
