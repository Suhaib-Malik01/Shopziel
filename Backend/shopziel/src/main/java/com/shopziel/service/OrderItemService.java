package com.shopziel.service;

import java.util.List;

import com.shopziel.dto.OrderItemDto;
import com.shopziel.dto.ProductDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.models.OrderItemStatus;
import com.shopziel.models.ReturnRequest;
import com.shopziel.models.ReturnRequestDto;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto) throws CustomerException, ProductException;

	OrderItemDto getOrderItemById(Integer orderItemId);

	List<OrderItemDto> getOrderItemsByProductId(Integer productId);

	List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId);

	OrderItemDto updateOrderItem(OrderItemDto orderItemDto);

	OrderItemDto cancelOrderItem(Integer orderItemId);

	OrderItemStatus viewOrderItemStatus(Integer orderItemId);
	
}
