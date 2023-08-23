package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.CurrentCustomerSession;

public interface CurrentCustomerSessionRepository extends JpaRepository<CurrentCustomerSession, Integer> {
	
	public CurrentCustomerSession findByToken(String token);
	
}
