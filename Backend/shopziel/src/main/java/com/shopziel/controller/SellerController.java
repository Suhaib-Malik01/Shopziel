package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.SellerDto;
import com.shopziel.service.SellerService;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "*")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    
    @PostMapping("/")
    public ResponseEntity<SellerDto> registerSeller(@RequestBody SellerDto sellerDto){
        
        return new ResponseEntity<SellerDto>(sellerService.registerSeller(sellerDto), HttpStatus.OK);
    }
}
