package com.shopziel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopziel.dto.CategoryDto;

import com.shopziel.models.Category;

import com.shopziel.repository.CategoryRepository;
import com.shopziel.exception.CategoryException;;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.save(modelMapper.map(categoryDto, Category.class));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) throws CategoryException {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found"));

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws CategoryException {

        categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new CategoryException("Category not found"));

        Category updatedCategory = modelMapper.map(categoryDto, Category.class);

        return modelMapper.map(categoryRepository.save(updatedCategory), CategoryDto.class);
    }

}
