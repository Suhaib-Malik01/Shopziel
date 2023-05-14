package com.shopziel.service;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.SellerException;

public interface ProductService {
    
    public ProductDto addProduct(ProductDto productDto) throws SellerException;
}
