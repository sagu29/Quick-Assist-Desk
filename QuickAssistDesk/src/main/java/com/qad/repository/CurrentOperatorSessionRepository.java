package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.CurrentOperatorSession;

public interface CurrentOperatorSessionRepository extends JpaRepository<CurrentOperatorSession, Integer> {

	public CurrentOperatorSession findByToken(String token);
	
}
