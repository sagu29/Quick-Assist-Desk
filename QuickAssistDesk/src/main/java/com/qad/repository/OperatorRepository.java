package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {

	public Operator findByUsername(String userName);
	
}
