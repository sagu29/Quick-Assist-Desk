package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {

	public Operator findByUsername(String userName);
	
}
