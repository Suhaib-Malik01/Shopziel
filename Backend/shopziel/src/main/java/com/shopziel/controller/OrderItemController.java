package com.shopziel.controller;

import com.shopziel.service.OrderItemService;
import com.shopziel.dto.OrderItemDto;
import com.shopziel.exception.CustomerException;
import com.shopziel.exception.ProductException;
import com.shopziel.Enum.OrderItemStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	/**
	 * Creates a new order item.
	 * 
	 * @param orderItemDto The order item data.
	 * @return The created order item.
	 * @throws CustomerException If the customer is not found.
	 * @throws ProductException  If the product is not found.
	 */
	@PostMapping("/")
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto)
			throws CustomerException, ProductException {

		OrderItemDto createdOrderItem = orderItemService.createOrderItem(orderItemDto);

		return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
	}

	/**
	 * Retrieves an order item by its ID.
	 * 
	 * @param orderItemId The ID of the order item.
	 * @return The order item.
	 */
	@GetMapping("/{orderItemId}")
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Integer orderItemId) {

		OrderItemDto orderItem = orderItemService.getOrderItemById(orderItemId);

		return ResponseEntity.ok(orderItem);
	}

	/**
	 * Retrieves order items by product ID.
	 * 
	 * @param productId The ID of the product.
	 * @return The list of order items associated with the product.
	 */
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<OrderItemDto>> getOrderItemsByProductId(@PathVariable Integer productId) {

		List<OrderItemDto> orderItems = orderItemService.getOrderItemsByProductId(productId);

		return ResponseEntity.ok(orderItems);
	}

	/**
	 * Retrieves all order items associated with an order.
	 * 
	 * @param orderId The ID of the order.
	 * @return The list of order items associated with the order.
	 */
	@GetMapping("/order/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getAllOrderItemsByOrderId(@PathVariable Integer orderId) {

		List<OrderItemDto> orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);

		return ResponseEntity.ok(orderItems);
	}

	/**
	 * Updates an existing order item.
	 * 
	 * @param orderItemDto The updated order item data.
	 * @return The updated order item.
	 */
	@PostMapping("/update")
	public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody OrderItemDto orderItemDto) {

		OrderItemDto updatedOrderItem = orderItemService.updateOrderItem(orderItemDto);

		return ResponseEntity.ok(updatedOrderItem);
	}

	/**
	 * Cancels an order item.
	 * 
	 * @param orderItemId The ID of the order item to cancel.
	 * @return The cancelled order item.
	 */
	@PostMapping("/cancel/{orderItemId}")
	public ResponseEntity<OrderItemDto> cancelOrderItem(@PathVariable Integer orderItemId) {

		OrderItemDto cancelledOrderItem = orderItemService.cancelOrderItem(orderItemId);

		return ResponseEntity.ok(cancelledOrderItem);
	}

	/**
	 * Retrieves the status of an order item.
	 * 
	 * @param orderItemId The ID of the order item.
	 * @return The status of the order item.
	 */
	@GetMapping("/status/{orderItemId}")
	public ResponseEntity<OrderItemStatus> viewOrderItemStatus(@PathVariable Integer orderItemId) {

		OrderItemStatus orderItemStatus = orderItemService.viewOrderItemStatus(orderItemId);

		return ResponseEntity.ok(orderItemStatus);
	}

	/**
	 * Retrieves all order items with pagination support.
	 * 
	 * @param pageSize      The number of order items per page.
	 * @param sortDirection The sorting direction for the order items.
	 * @param pageNo        The page number to retrieve.
	 * @return The paginated list of order items.
	 */
	@GetMapping("/")
	public ResponseEntity<Page<OrderItemDto>> getAllOrderItems(@RequestParam Integer pageSize,
			@RequestParam String sortDirection, @RequestParam Integer pageNo) {

		Page<OrderItemDto> orderItemsPage = orderItemService.getAllOrderItems(pageSize, sortDirection, pageNo);

		return ResponseEntity.ok(orderItemsPage);
	}
}
