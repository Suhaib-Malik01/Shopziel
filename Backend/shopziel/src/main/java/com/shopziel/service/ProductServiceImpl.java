package com.shopziel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shopziel.dto.ProductDto;
import com.shopziel.exception.ProductException;
import com.shopziel.exception.SellerException;
import com.shopziel.models.Product;
import com.shopziel.models.Seller;
import com.shopziel.repository.ProductRepository;
import com.shopziel.repository.SellerRepository;

@Service
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

        if (auth != null) {
            userEmail = auth.getPrincipal().toString();
        } else {
            throw new SellerException("Login Expired...");
        }

        Product product = modelMapper.map(productDto, Product.class);

        Seller seller = sellerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new SellerException("Seller Not Found..."));

        product.setSeller(seller);

        product = productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws ProductException, SellerException {

        String userEmail;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            userEmail = auth.getPrincipal().toString();
        } else {
            throw new SellerException("Login Expired...");
        }

        Seller seller = sellerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new SellerException("Seller Not Found..."));

        Product product = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new ProductException("Product not found with ID: " + productDto.getProductId()));

        if (product.getSeller().getId() != seller.getId())
            throw new SellerException("You are not authorized to perform this operation.");

        product = productRepository.save(modelMapper.map(productDto, Product.class));

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() throws ProductException {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty())
            throw new ProductException("Products not available");

        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto deleteProduct(int id) throws ProductException, SellerException {

        String userEmail;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            userEmail = auth.getPrincipal().toString();
        } else {
            throw new SellerException("Login Expired...");
        }

        Seller seller = sellerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new SellerException("Seller Not Found..."));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with ID: " + id));

        if (product.getSeller().getId() != seller.getId())
            throw new SellerException("You are not authorized to perform this operation.");

        productRepository.delete(product);

        return modelMapper.map(product, ProductDto.class);

    }

    public ProductDto getProduct(Integer id) throws ProductException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with ID: " + id));

        return modelMapper.map(product, ProductDto.class);
    }

}
