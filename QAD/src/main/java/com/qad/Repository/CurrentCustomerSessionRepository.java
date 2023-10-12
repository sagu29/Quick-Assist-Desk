package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentCustomerSession;

public interface CurrentCustomerSessionRepository extends JpaRepository<CurrentCustomerSession, Integer> {
	// Custom query method to find a current customer session by token
	public CurrentCustomerSession findByToken(String token);
	
}
