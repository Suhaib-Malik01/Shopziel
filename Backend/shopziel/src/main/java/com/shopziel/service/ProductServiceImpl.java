package com.shopziel.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.SellerException;
import com.shopziel.models.Product;
import com.shopziel.models.Seller;
import com.shopziel.repository.ProductRepository;
import com.shopziel.repository.SellerRepository;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) throws SellerException {

        String userEmail;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth!=null){
            userEmail = auth.getPrincipal().toString();
        }else{
            throw new SellerException("Login Expired...");
        }

        Product product = modelMapper.map(productDto, Product.class);


        Seller seller = sellerRepository.findByEmail(userEmail).orElseThrow(() -> new SellerException("Seller Not Found..."));

        product.setSeller(seller);


        product = productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

}
