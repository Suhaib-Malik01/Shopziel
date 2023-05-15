package com.shopziel.service;

import com.shopziel.dto.ReviewDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.ReviewException;

public interface ReviewService {
    public ReviewDto addReview(Integer id, ReviewDto reviewDto) throws ProductException, CustomerException;

    public ReviewDto updateReview(Integer id, ReviewDto reviewDto) throws CustomerException, ProductException, ReviewException;

    public ReviewDto deleteReview(Integer reviewId) throws ReviewException;
}
