package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.CurrentAdminSession;

public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {

	public CurrentAdminSession findByToken(String token);
	
}
