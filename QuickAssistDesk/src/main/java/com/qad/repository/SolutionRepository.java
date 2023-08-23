package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.Solution;

public interface SolutionRepository extends JpaRepository<Solution, Integer> {

}
