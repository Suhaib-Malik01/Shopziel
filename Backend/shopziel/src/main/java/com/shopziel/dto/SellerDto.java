package com.shopziel.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SellerDto extends UserDto {
    
    List<ProductDto> products = new ArrayList<>(); 
}
