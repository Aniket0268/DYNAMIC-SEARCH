package com.brainworks.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brainworks.entity.EligibilityDtlsEntity;
@Repository
public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDtlsEntity, Serializable> {
	@Query("select distinct(planName) from EligibilityDtlsEntity")
	public List<String> getUniquePlanName();
	@Query("select distinct(planStatus) from EligibilityDtlsEntity")
	public List<String> getUniquePlanStatus();
}
 