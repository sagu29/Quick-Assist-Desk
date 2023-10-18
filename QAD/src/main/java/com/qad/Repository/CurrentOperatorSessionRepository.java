package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.CurrentOperatorSession;

public interface CurrentOperatorSessionRepository extends JpaRepository<CurrentOperatorSession, Integer> {
	// Custom query method to find a current operator session by token
	public CurrentOperatorSession findByToken(String token);
	
}
