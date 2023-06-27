package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.CategoryException;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.SellerException;
import com.shopziel.service.ProductService;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class SellerController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws SellerException, CategoryException {

        return new ResponseEntity<ProductDto>(productService.addProduct(productDto), HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto)
            throws ProductException, SellerException {

        return new ResponseEntity<ProductDto>(productService.updateProduct(productDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Integer id) throws ProductException, SellerException {

        return new ResponseEntity<ProductDto>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
