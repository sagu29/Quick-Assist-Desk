package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

}
