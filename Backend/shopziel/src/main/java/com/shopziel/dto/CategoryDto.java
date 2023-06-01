package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CategoryDto {

    private Integer categoryId;

    private String image;

    private String name;

    private List<ProductDto> products = new ArrayList<>();
}
