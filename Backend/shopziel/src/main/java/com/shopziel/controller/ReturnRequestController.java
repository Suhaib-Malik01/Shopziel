package com.shopziel.controller;

import com.shopziel.service.ReturnRequestService;
import com.shopziel.dto.ReturnRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for handling Return Request operations.
 */
@RestController
@RequestMapping("/api/return-requests")
public class ReturnRequestController {
	
	@Autowired
	private ReturnRequestService returnRequestService;

	/**
	 * Endpoint for raising a new return request.
	 * 
	 * @param returnRequestDto The return request data.
	 * @return The created return request.
	 */
	@PostMapping("/")
	public ResponseEntity<ReturnRequestDto> raiseReturnRequest(@RequestBody ReturnRequestDto returnRequestDto) {
		
		ReturnRequestDto raisedReturnRequest = returnRequestService.raiseReturnRequest(returnRequestDto);
		
		return new ResponseEntity<>(raisedReturnRequest, HttpStatus.CREATED);
	}

	/**
	 * Endpoint for retrieving return requests for sellers. Requires "ROLE_SELLER"
	 * authority.
	 * 
	 * @return The list of return requests for sellers.
	 */
	@GetMapping("/seller")
	public ResponseEntity<List<ReturnRequestDto>> viewReturnRequestsForSeller() {
		
		List<ReturnRequestDto> returnRequestsForSeller = returnRequestService.viewReturnRequestsForSeller();
		
		return ResponseEntity.ok(returnRequestsForSeller);
	}

	/**
	 * Endpoint for retrieving all return requests. Requires "ROLE_ADMIN" authority.
	 * 
	 * @param pageSize      The number of return requests to retrieve per page.
	 * @param sortDirection The sorting direction for the return requests.
	 * @param pageNo        The page number to retrieve.
	 * @return The paginated list of all return requests.
	 */
	@GetMapping("/")
	public ResponseEntity<Page<ReturnRequestDto>> viewAllReturnRequests(@RequestParam Integer pageSize,
			@RequestParam String sortDirection, @RequestParam Integer pageNo) {
		
		Page<ReturnRequestDto> returnRequestsPage = returnRequestService.viewAllReturnRequests(pageSize, sortDirection,
				pageNo);
		
		return ResponseEntity.ok(returnRequestsPage);
	}
}
