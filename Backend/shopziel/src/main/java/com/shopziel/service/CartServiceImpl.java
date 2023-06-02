package com.shopziel.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.dto.CartDto;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.OrderItemException;
import com.shopziel.exception.ProductException;
import com.shopziel.models.Customer;
import com.shopziel.models.OrderItem;
import com.shopziel.models.Product;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.ProductRepository;

/**
 * Service class for managing the shopping cart.
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * Adds an item to the shopping cart.
	 *
	 * @param orderItemDto The DTO object containing the order item details.
	 * @return The DTO object representing the added order item.
	 */
	@Override
	public OrderItemDto addToCart(OrderItemDto orderItemDto) {

		// Get the logged-in customer
		Customer customer = this.sessionService.getLoggedInCustomer();

		// Map the orderItemDto to an OrderItem entity
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(orderItemDto.getQuantity());

		Product product = productRepository.findById(orderItemDto.getProductId())
				.orElseThrow(() -> new ProductException("Product not found with ID: " + orderItemDto.getProductId()));

		orderItem.setPrice(product.getPrice() * orderItemDto.getQuantity());
		orderItem.setProduct(product);

		orderItem.setStatus(OrderItemStatus.IN_CART);

		customer.getCart().add(orderItem);

		// Save the customer and order item in the repositories
		orderItem = orderItemRepository.save(orderItem);
		customerRepository.save(customer);

		// Map the saved order item to an OrderItemDto and return it
		return this.modelMapper.map(orderItem, OrderItemDto.class);
	}

	/**
	 * Removes an item from the shopping cart.
	 *
	 * @param orderItemDto The DTO object representing the order item to remove.
	 * @return The DTO object representing the removed order item.
	 */
	@Override
	public OrderItemDto removeFromCart(Integer orderItemId) {

		Customer customer = this.sessionService.getLoggedInCustomer();

		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("OrderItem not found with ID: " + orderItemId));

		if (!customer.getCart().contains(orderItem)) {
			throw new CustomerException("You are not authorized to delete this orderItem");
		}

		customer.getCart().remove(orderItem);

		customerRepository.save(customer);

		return modelMapper.map(orderItem, OrderItemDto.class);
	}

	/**
	 * Proceeds to the payment process.
	 *
	 * @return The DTO object representing the order for payment.
	 */
	@Override
	public OrderDto proceedToPayment() {
		// TODO: Implement the payment process
		return null;
	}

	/**
	 * Calculates the total price of the items in the shopping cart.
	 *
	 * @return The total price of the items in the cart.
	 */
	@Override
	public Double getCartTotal() {

		// Get the logged-in customer
		Customer customer = this.sessionService.getLoggedInCustomer();

		Double cartTotal = 0.00;

		for (OrderItem orderItem : customer.getCart()) {
			// Calculate the total price by multiplying the price with quantity for each
			// item
			cartTotal += (orderItem.getPrice());
		}

		// Return the cart total
		return cartTotal;
	}

	@Override
	public CartDto getOrderItemsOfCart() {
		Set<OrderItem> cart = this.sessionService.getLoggedInCustomer().getCart();
		CartDto cartDto = new CartDto();
		cartDto.setCartItems(cart.stream().map((item) -> this.modelMapper.map(item, OrderItemDto.class)).toList());
		cartDto.setCartTotal(getCartTotal());
		cartDto.setTotalProducts(cartDto.getCartItems().size());
		return cartDto;
	}

	@Override
	public OrderItemDto updateQuantity(int orderItemId, int quantity) {
		Customer customer = this.sessionService.getLoggedInCustomer();

		OrderItem orderItem = this.orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new OrderItemException("Incorrect Order Id"));

		Set<OrderItem> cart = customer.getCart();

		if (cart.contains(orderItem)) {
			cart.remove(orderItem);
			orderItem.setQuantity(quantity);
			orderItem.setPrice(orderItem.getProduct().getPrice() * quantity);
			cart.add(orderItem);
			orderItemRepository.save(orderItem);
			customerRepository.save(customer);

			return modelMapper.map(orderItem, OrderItemDto.class);
		} else
			throw new OrderItemException("Order Item with Order Item Id : " + orderItemId + " dosent belong to you!!");
	}
}
