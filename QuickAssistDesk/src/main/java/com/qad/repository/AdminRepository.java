package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByUsername(String userName);
	
}
