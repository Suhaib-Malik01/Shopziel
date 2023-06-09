package com.shopziel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.dto.OrderItemDto;

import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto) throws CustomerException, ProductException;

	OrderItemDto getOrderItemById(Integer orderItemId);

	List<OrderItemDto> getOrderItemsByProductId(Integer productId);

	List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId);

	OrderItemDto updateOrderItem(OrderItemDto orderItemDto);

	OrderItemDto cancelOrderItem(Integer orderItemId);

	OrderItemStatus viewOrderItemStatus(Integer orderItemId);

	Page<OrderItemDto> getAllOrderItems(Integer pageSize, String sortDirection, Integer pageNo);
}
