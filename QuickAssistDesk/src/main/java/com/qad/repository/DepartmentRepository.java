package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
