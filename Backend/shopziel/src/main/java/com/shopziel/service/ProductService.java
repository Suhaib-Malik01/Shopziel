package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.SellerException;

public interface ProductService {
    
    public ProductDto addProduct(ProductDto productDto) throws SellerException;

    public ProductDto updateProduct(ProductDto productDto) throws ProductException, SellerException;

    public List<ProductDto> getAllProducts() throws ProductException;

    public ProductDto deleteProduct(int id) throws ProductException, SellerException;
}
