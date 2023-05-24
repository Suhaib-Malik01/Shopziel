package com.shopziel.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shopziel.Enum.OrderItemStatus;
import com.shopziel.dto.ReturnRequestDto;
import com.shopziel.exception.OrderItemException;
import com.shopziel.models.OrderItem;
import com.shopziel.models.ReturnRequest;
import com.shopziel.models.Seller;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.ReturnRequestRepository;

/**
 * Service implementation for managing return requests.
 */
public class ReturnRequestServiceImpl implements ReturnRequestService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ReturnRequestRepository returnRequestRepository;

	/**
	 * Creates a new return request.
	 *
	 * @param returnRequestDto The return request DTO containing the request details.
	 * @return The created return request DTO.
	 */
	@Override
	public ReturnRequestDto raiseReturnRequest(ReturnRequestDto returnRequestDto) {
		// Map the ReturnRequestDto to ReturnRequest entity
		ReturnRequest returnRequest = modelMapper.map(returnRequestDto, ReturnRequest.class);

		// Find the order item by ID
		OrderItem orderItem = orderItemRepository.findById(returnRequestDto.getOrderItem().getItemId())
				.orElseThrow(() -> new OrderItemException(
						"Order item not found with order id : " + returnRequestDto.getOrderItem().getItemId()));

		// Update the status of the order item to RETURN_REQUESTED
		orderItem.setStatus(OrderItemStatus.RETURN_REQUESTED);

		// Set the order item and other details in the return request
		returnRequest.setOrderItem(orderItem);
		returnRequest.setCustomer(sessionService.getLoggedInCustomer());
		returnRequest.setRequestRaisedDate(new Date(System.currentTimeMillis()));

		// Save the updated order item and return request
		orderItemRepository.save(orderItem);
		returnRequest = returnRequestRepository.save(returnRequest);

		// Map the created return request to ReturnRequestDto and return
		return modelMapper.map(returnRequest, ReturnRequestDto.class);
	}

	/**
	 * Retrieves all return requests for the logged-in seller.
	 *
	 * @return A list of return request DTOs for the seller's products.
	 */
	@Override
	public List<ReturnRequestDto> viewReturnRequestsForSeller() {
		// Get the logged-in seller
		Seller seller = sessionService.getLoggedInSeller();

		// Find all return requests associated with the seller's products
		List<ReturnRequest> returnRequests = returnRequestRepository.findByOrderItemProductSeller(seller);

		// Map the return requests to ReturnRequestDto and return as a list
		return returnRequests.stream().map(req -> modelMapper.map(req, ReturnRequestDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves a page of all return requests.
	 *
	 * @param pageSize       The number of return requests per page.
	 * @param sortDirection  The sort direction for the requestRaisedDate field (ASC or DESC).
	 * @param pageNo         The page number to retrieve.
	 * @return A page of return request DTOs.
	 */
	@Override
	public Page<ReturnRequestDto> viewAllReturnRequests(Integer pageSize, String sortDirection, Integer pageNo) {
		// Set the default sort as descending order based on requestRaisedDate
		Sort sort = Sort.by("requestRaisedDate").descending();

		// Check if the sort direction is ascending, then update the sort accordingly
		if (sortDirection != null && sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by("requestRaisedDate").ascending();
		}

		// Create a pageable object with the specified pageSize, sort, and pageNo
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		// Retrieve a page of return requests using the pageable object
		Page<ReturnRequest> returnRequestPage = returnRequestRepository.findAll(pageable);

		// Map the return requests to ReturnRequestDto
		List<ReturnRequestDto> returnRequestDtoList = returnRequestPage.stream()
				.map(req -> modelMapper.map(req, ReturnRequestDto.class)).collect(Collectors.toList());

		// Create a new PageImpl with the mapped return requests, pageable, and total elements count
		return new PageImpl<>(returnRequestDtoList, pageable, returnRequestPage.getTotalElements());
	}
}
