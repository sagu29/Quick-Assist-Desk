package com.qad.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int solutionId;
	    private String solutionDescription;
	    private Date solutionDate;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "issue_id")
	    private Issue issue;
}
