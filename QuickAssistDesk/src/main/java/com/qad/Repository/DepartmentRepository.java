package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
