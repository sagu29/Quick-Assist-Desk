package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Admin;
import com.qad.Entity.Customer;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	// Custom query method to find an admin by their username
	public Admin findByUsername(String userName);
	
}
