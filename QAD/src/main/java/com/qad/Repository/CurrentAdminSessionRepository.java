package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentAdminSession;

public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {

	public CurrentAdminSession findByToken(String token);
	
}
