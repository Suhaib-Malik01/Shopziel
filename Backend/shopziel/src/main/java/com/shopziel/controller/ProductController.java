package com.shopziel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.ProductException;
import com.shopziel.service.ProductService;

@RestController
@RequestMapping("/api/products")
 @CrossOrigin (origins = "*" , exposedHeaders = "**")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts() throws ProductException {

        return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) throws ProductException {

        return new ResponseEntity<ProductDto>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable("keyword") String keyword) {

        List<ProductDto> searchResults = productService.searchProducts(keyword);

        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
