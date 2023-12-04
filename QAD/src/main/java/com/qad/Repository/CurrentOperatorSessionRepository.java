package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentOperatorSession;

public interface CurrentOperatorSessionRepository extends JpaRepository<CurrentOperatorSession, Integer> {

	public CurrentOperatorSession findByToken(String token);
	
}
