package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentAdminSession;

public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {
	
	// Custom query method to find a current admin session by token
	public CurrentAdminSession findByToken(String token);
	
}
