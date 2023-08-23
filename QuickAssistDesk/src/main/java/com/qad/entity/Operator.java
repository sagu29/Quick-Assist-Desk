package com.qad.entity;

import java.util.ArrayList;


import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer operatorId;

	@NotNull(message = "Name field should not be empty")
	@Column(name =  "name")
	private String name;
	
	@NotNull(message="Email is mandatory")
	@Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",message = "Input a valid email address")
	private String email;
	
	private String username;
	
	private String password;
	
	@NotNull(message = "Mobile number field should not be empty")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Input a valid mobile number")
	@Column(name = "mobile")
	private String mobile;
	
	@NotNull(message = "City field should not be empty")
	@Column(name = "city")
	private String city;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "operator")
	private List<Call> call = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	private Department department;
	
}
