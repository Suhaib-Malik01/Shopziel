package com.shopziel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopziel.dto.CategoryDto;
import com.shopziel.exception.CategoryException;
import com.shopziel.service.CategoryService;



@RestController
@RequestMapping("/api/admin/category")
 @CrossOrigin (origins = "*" , exposedHeaders = "**")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){


        return new ResponseEntity<CategoryDto>(categoryService.addCategory(categoryDto), HttpStatus.OK);
    }
    

    @PutMapping("/")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) throws CategoryException{


        return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto), HttpStatus.OK);
    }
}
