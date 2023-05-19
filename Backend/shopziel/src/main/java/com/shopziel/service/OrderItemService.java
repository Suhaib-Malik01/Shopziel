package com.shopziel.service;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto) throws CustomerException, ProductException ;
	
	OrderItemDto getOrderItemById(Integer orderItemId);
	
	OrderItemDto getOrderItemByProductId(Integer productId);
	
	OrderItemDto getAllOrderItemsByOrderId(Integer orderId);
	
	OrderItemDto updateOrderItem(OrderItemDto orderItemDto);
	
	OrderItemDto removeOrderItemFromOrder();
}
