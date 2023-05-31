package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.CategoryException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.SellerException;

public interface ProductService {
    
    public ProductDto addProduct(ProductDto productDto) throws SellerException, CategoryException;

    public ProductDto updateProduct(ProductDto productDto) throws ProductException, SellerException;

    public List<ProductDto> getAllProducts() throws ProductException;

    public ProductDto deleteProduct(int id) throws ProductException, SellerException;

    public ProductDto getProduct(Integer id) throws ProductException;

    public List<ProductDto> searchProducts(String keyword);
}
