package com.qad.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer issueId;
	
	@NotNull(message = "IssueTpe Name field should not be empty")
	@Column(name =  "issueType")
	private String issueType;
	
	@NotBlank(message = "{blank.invalid}")
	@NotEmpty(message = "{empty.invalid}")
	@Size(min = 10,message = "Description cannot be less than 10 characters")
	private String description;
	
	@NotNull(message = "Status field should not be empty")
	@Column(name =  "status")
	private IssueStatus status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "callID")
	private Call call;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "solution_id")
	private Solution solution;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "customer_id")
	private Customer customer;
	
}
