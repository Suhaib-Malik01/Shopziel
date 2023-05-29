package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.CategoryDto;

import com.shopziel.exception.CategoryException;

public interface CategoryService {

    public CategoryDto addCategory(CategoryDto categoryDto);
    
    public List<CategoryDto> getAllCategory();

    public CategoryDto getCategory(Integer categoryId) throws CategoryException;

    
}
