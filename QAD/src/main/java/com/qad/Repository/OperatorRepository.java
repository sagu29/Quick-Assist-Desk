package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {
	// Custom query method to find an operator by username
	public Operator findByUsername(String userName);
	
}
