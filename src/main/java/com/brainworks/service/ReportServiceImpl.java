package com.brainworks.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.brainworks.entity.EligibilityDtlsEntity;
import com.brainworks.repository.EligibilityDtlsRepo;
import com.brainworks.request.SearchRequest;
import com.brainworks.response.SearchResponse;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private EligibilityDtlsRepo eligRepo;

	@Override
	public List<String> getPlanNames() {

		return eligRepo.getUniquePlanName();
	}

	@Override
	public List<String> getPlanStatuses() {
		return eligRepo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityDtlsEntity> eligRecords = null;

		//if (request == null) 
		
		if(isSearchRequestEmpty(request)){
			eligRecords = eligRepo.findAll();
		} else {

			String planeName = request.getPlaneName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();
			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();
			if (planeName != null && !planeName.equals("")) {
				entity.setPlanName(planeName);
				

			}
			if (planStatus != null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null && !startDate.equals("")) {
				entity.setStartDate(startDate);

			}
			if (endDate != null && !endDate.equals("")) {
				entity.setEndDate(endDate);

			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			 eligRecords = eligRepo.findAll(of);
			
		}
		List<SearchResponse> response = new ArrayList<>();
		for(EligibilityDtlsEntity eligRecord:eligRecords) {
			SearchResponse re =new SearchResponse();
			BeanUtils.copyProperties(eligRecord, re);
			response.add(re);
		}

		return response;
	}
	private boolean isSearchRequestEmpty(SearchRequest request) {
		boolean isEmpty = true;
		if(request.getPlaneName() !=null && request.getPlaneName().equals("")) {
			isEmpty = false;
		}
		if(request.getPlanStatus() !=null && request.getPlanStatus().equals("")) {
			isEmpty = false;
		}
		if(request.getStartDate() !=null && request.getStartDate().equals("")) {
			isEmpty = false;
		}
		if(request.getEndDate() !=null && request.getEndDate().equals("")) {
			isEmpty = false;
		}
		return isEmpty;
	}

}
