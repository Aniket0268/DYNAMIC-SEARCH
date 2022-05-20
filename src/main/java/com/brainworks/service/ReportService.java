package com.brainworks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brainworks.request.SearchRequest;
import com.brainworks.response.SearchResponse;

@Service
public interface ReportService {
	public List<String> getPlanNames();
	public List<String> getPlanStatuses();
	public List<SearchResponse> searchPlans(SearchRequest request);
	

}
