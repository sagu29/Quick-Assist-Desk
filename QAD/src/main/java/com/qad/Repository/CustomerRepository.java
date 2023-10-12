package com.qad.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	// Custom query method to find a customer by email
	Optional<Customer> findByEmail(String email);
	
	 // Custom query method to find a customer by username
	public Customer findByUsername(String userName);
	
	 // Custom query method to find customers by name
	List<Customer> findByName(String name);
	
}
