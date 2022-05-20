package com.brainworks.request;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SearchRequest {
	private String planeName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;

}
