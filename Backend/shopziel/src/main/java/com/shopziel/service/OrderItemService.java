package com.shopziel.service;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.ProductDto;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto) ;
	
	OrderItemDto getOrderItemById(Integer orderItemId);
	
	OrderItemDto getOrderItemByProductId(Integer productId);
	
	OrderItemDto getAllOrderItemsByOrderId(Integer orderId);
	
	OrderItemDto updateOrderItem(OrderItemDto orderItemDto);
	
	OrderItemDto removeOrderItemFromOrder();
}
