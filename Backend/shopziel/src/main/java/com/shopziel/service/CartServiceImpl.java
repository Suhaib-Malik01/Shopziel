package com.shopziel.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.dto.CartDto;
import com.shopziel.dto.OrderDto;
import com.shopziel.dto.OrderItemDto;
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

		if (!customer.getCart().contains(orderItem)) {
			// Add the order item to the cart if it's not already present
			customer.getCart().add(orderItem);
		} else {
			// Increase the quantity if the order item is already in the cart
			int i = customer.getCart().indexOf(orderItem);
			customer.getCart().get(i).setQuantity(customer.getCart().get(i).getQuantity() + 1);
		}

		// Save the customer and order item in the repositories
		customerRepository.save(customer);
		orderItem = orderItemRepository.save(orderItem);

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
	public OrderItemDto removeFromCart(OrderItemDto orderItemDto) {

		// Get the logged-in customer
		Customer customer = this.sessionService.getLoggedInCustomer();

		// Map the orderItemDto to an OrderItem entity
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);

		if (customer.getCart().contains(orderItem)) {
			// Remove the order item from the cart if it's present
			customer.getCart().remove(orderItem);
		}

		// Save the customer and delete the order item from the repository
		customerRepository.save(customer);
		orderItemRepository.delete(orderItem);

		// Return the removed order item DTO
		return orderItemDto;
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
			cartTotal += (orderItem.getPrice() * orderItem.getQuantity());
		}

		// Return the cart total
		return cartTotal;
	}

	@Override
	public CartDto getOrderItemsOfCart() {
		List<OrderItem> cart = this.sessionService.getLoggedInCustomer().getCart();
		CartDto cartDto = new CartDto();
		cartDto.setCartItems(cart.stream().map((item) -> this.modelMapper.map(item, OrderItemDto.class)).toList());
		cartDto.setCartTotal(getCartTotal());
		cartDto.setTotalProducts(cartDto.getCartItems().size());
		return cartDto;
	}
}
