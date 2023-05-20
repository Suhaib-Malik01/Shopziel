package com.shopziel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopziel.dto.ReturnRequestDto;

public interface ReturnRequestService {

	ReturnRequestDto raiseReturnRequest(ReturnRequestDto returnRequest);

	List<ReturnRequestDto> viewReturnRequestsForSeller();

	Page<ReturnRequestDto> viewAllReturnRequests(Integer pageSize, String sortDirection, Integer pageNo);
}
