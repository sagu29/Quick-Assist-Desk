package com.qad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.entity.Issue;

public interface IssueRepo extends JpaRepository<Issue,Integer>{

}
