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
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.ReviewDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.ReviewException;
import com.shopziel.service.CartService;
import com.shopziel.service.ReviewService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CartService cartService;

	@PostMapping("/review/{id}")
	public ResponseEntity<ReviewDto> addReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto)
			throws ProductException, CustomerException {

		return new ResponseEntity<ReviewDto>(reviewService.addReview(id, reviewDto), HttpStatus.OK);
	}

	@PutMapping("/review/{id}")
	public ResponseEntity<ReviewDto> updateReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto)
			throws CustomerException, ProductException, ReviewException {

		return new ResponseEntity<ReviewDto>(reviewService.updateReview(id, reviewDto), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<ReviewDto> deleteReview(@PathVariable Integer id) throws ReviewException {

		return new ResponseEntity<ReviewDto>(reviewService.deleteReview(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("/cart")
	public ResponseEntity<List<OrderItemDto>> getCartItems() {
		return new ResponseEntity<List<OrderItemDto>>(cartService.getOrderItemsOfCart(), HttpStatus.OK);
	}
}
