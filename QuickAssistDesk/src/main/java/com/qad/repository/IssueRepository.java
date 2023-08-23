package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qad.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
