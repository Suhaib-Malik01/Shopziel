package com.shopziel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.AddressDto;
import com.shopziel.dto.CartDto;
import com.shopziel.dto.CustomerDto;
import com.shopziel.dto.OfferDto;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.ReviewDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.ReviewException;

import com.shopziel.service.CustomerService;
import com.shopziel.service.OfferService;
import com.shopziel.service.CartService;

import com.shopziel.service.ReviewService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/customers")
 @CrossOrigin (origins = "*" , exposedHeaders = "**")

public class CustomerController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OfferService offerService;

	/**
	 * 
	 * @param id
	 * @param reviewDto
	 * @return ResponseEntity<ReviewDto>
	 * @throws CustomerException
	 * @throws ProductException
	 * @throws ReviewException
	 */
	@PutMapping("/review/{id}")
	public ResponseEntity<ReviewDto> updateReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto)
			throws CustomerException, ProductException, ReviewException {

		return new ResponseEntity<ReviewDto>(reviewService.updateReview(id, reviewDto), HttpStatus.ACCEPTED);
	}

	@PostMapping("/review/{id}")
	public ResponseEntity<ReviewDto> addReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto)
			throws ProductException, CustomerException {

		return new ResponseEntity<ReviewDto>(reviewService.addReview(id, reviewDto), HttpStatus.OK);
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<ReviewDto> deleteReview(@PathVariable Integer id) throws ReviewException {

		return new ResponseEntity<ReviewDto>(reviewService.deleteReview(id), HttpStatus.ACCEPTED);

	}

	@GetMapping("/")
	public ResponseEntity<CustomerDto> getCustomerDetails() {

		return new ResponseEntity<CustomerDto>(customerService.getCustomer(), HttpStatus.OK);
	}

	@GetMapping("/cart")
	public ResponseEntity<CartDto> getCartItems() {

		return new ResponseEntity<CartDto>(cartService.getOrderItemsOfCart(), HttpStatus.OK);
	}

	@PutMapping("/cart/")
	public ResponseEntity<OrderItemDto> updateOrderItem(
			@RequestParam("quantity") @Min(value = 1, message = "Quantity must be at least 1") @Max(value = 7, message = "Quantity cannot exceed 7") @Valid int quantity,
			@RequestParam("orderItemId") int orderItemId) {
		return new ResponseEntity<OrderItemDto>(cartService.updateQuantity(orderItemId, quantity), HttpStatus.OK);
	}

	@PostMapping("/cart/add")
	public ResponseEntity<OrderItemDto> addToCart(@RequestBody OrderItemDto orderItemDto) {
		return new ResponseEntity<OrderItemDto>(cartService.addToCart(orderItemDto), HttpStatus.OK);
	}

	@DeleteMapping("/cart/{orderItemId}")
	public ResponseEntity<OrderItemDto> deleteFromCart(@PathVariable Integer orderItemId) {

		return new ResponseEntity<OrderItemDto>(cartService.removeFromCart(orderItemId), HttpStatus.OK);
	}

	@GetMapping("/offers")
	public ResponseEntity<List<OfferDto>> getAllOffers() {

		return new ResponseEntity<List<OfferDto>>(offerService.getAllOffers(), HttpStatus.OK);
	}

	@PostMapping("/address")
	public ResponseEntity<CustomerDto> addAddress(@RequestBody AddressDto addressDto) {
		return new ResponseEntity<CustomerDto>(this.customerService.addAddress(addressDto), HttpStatus.OK);
	}

	@GetMapping("/address")
	public ResponseEntity<List<AddressDto>> getAllAddressesOfCustomer() {
		return new ResponseEntity<List<AddressDto>>(this.customerService.getAddress(), HttpStatus.OK);

	}

}
