package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shopziel.dto.ReviewDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.ReviewException;
import com.shopziel.models.Customer;
import com.shopziel.models.Product;
import com.shopziel.models.Review;
import com.shopziel.repository.CustomerRepository;
import com.shopziel.repository.ProductRepository;
import com.shopziel.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ReviewDto addReview(Integer id, ReviewDto reviewDto) throws ProductException, CustomerException {

        String userEmail;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            userEmail = auth.getPrincipal().toString();
        } else {
            throw new CustomerException("Login expired..");
        }

        Customer customer = customerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomerException("Customer not Found with Email: " + userEmail));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with ID: " + id));

        Review review = modelMapper.map(reviewDto, Review.class);

        review.setProduct(product);
        review.setCustomer(customer);

        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public ReviewDto updateReview(Integer id, ReviewDto reviewDto)
            throws CustomerException, ProductException, ReviewException {

        String userEmail;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            userEmail = auth.getPrincipal().toString();
        } else {
            throw new CustomerException("Login expired..");
        }

        Customer customer = customerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomerException("Customer not Found with Email: " + userEmail));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with ID: " + id));

        Review review = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new ReviewException("Review not found with ID: " + reviewDto.getId()));

        if (review.getCustomer().getId() != customer.getId())
            throw new CustomerException("You are not authorized to edit this review.");

        if (review.getProduct().getProductId() != product.getProductId())
            throw new ProductException("Invalid Product ID...");

        review = modelMapper.map(reviewDto, Review.class);

        review.setCustomer(customer);
        review.setProduct(product);

        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);

    }

    @Override
    public ReviewDto deleteReview(Integer reviewId) throws ReviewException {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("Review not found with ID: " + reviewId));

        reviewRepository.delete(review);

        return modelMapper.map(review, ReviewDto.class);
    }

}
