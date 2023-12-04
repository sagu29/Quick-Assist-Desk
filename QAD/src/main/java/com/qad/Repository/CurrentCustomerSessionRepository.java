package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentCustomerSession;

public interface CurrentCustomerSessionRepository extends JpaRepository<CurrentCustomerSession, Integer> {
	
	public CurrentCustomerSession findByToken(String token);
	
}
