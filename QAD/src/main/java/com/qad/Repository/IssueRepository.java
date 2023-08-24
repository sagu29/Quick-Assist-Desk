package com.qad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qad.Entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
