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

import com.shopziel.exception.OrderItemException;
import com.shopziel.models.OrderItem;
import com.shopziel.models.OrderItemStatus;
import com.shopziel.models.ReturnRequest;
import com.shopziel.models.ReturnRequestDto;
import com.shopziel.models.Seller;
import com.shopziel.repository.OrderItemRepository;
import com.shopziel.repository.ReturnRequestRepository;

public class ReturnRequestServiceImpl implements ReturnRequestService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ReturnRequestRepository returnRequestRepository;

	@Override
	public ReturnRequestDto raiseReturnRequest(ReturnRequestDto returnRequestDto) {

		ReturnRequest returnRequest = modelMapper.map(returnRequestDto, ReturnRequest.class);

		OrderItem orderItem = orderItemRepository.findById(returnRequestDto.getOrderItem().getItemId())
				.orElseThrow(() -> new OrderItemException(
						"Order item not found with order id : " + returnRequestDto.getOrderItem().getItemId()));

		orderItem.setStatus(OrderItemStatus.RETURN_REQUESTED);

		returnRequest.setOrderItem(orderItem);
		returnRequest.setCustomer(sessionService.getLoggedInCustomer());
		returnRequest.setRequestRaisedDate(new Date(System.currentTimeMillis()));

		orderItemRepository.save(orderItem);
		returnRequest = returnRequestRepository.save(returnRequest);

		return modelMapper.map(returnRequest, ReturnRequestDto.class);
	}

	@Override
	public List<ReturnRequestDto> viewReturnRequestsForSeller() {

		Seller seller = sessionService.getLoggedInSeller();

		List<ReturnRequest> returnRequests = returnRequestRepository.findByOrderItemProductSeller(seller);

		return returnRequests.stream().map(req -> modelMapper.map(req, ReturnRequestDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public Page<ReturnRequestDto> viewAllReturnRequests(Integer pageSize, String sortDirection) {
		Sort sort = Sort.by("requestRaisedDate").descending();

		if (sortDirection != null && sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by("requestRaisedDate").ascending();
		}

		Pageable pageable = PageRequest.of(0, pageSize, sort);

		Page<ReturnRequest> returnRequestPage = returnRequestRepository.findAll(pageable);

		List<ReturnRequestDto> returnRequestDtoList = returnRequestPage.stream()
				.map(req -> modelMapper.map(req, ReturnRequestDto.class)).collect(Collectors.toList());

		return new PageImpl<>(returnRequestDtoList, pageable, returnRequestPage.getTotalElements());

	}

}
