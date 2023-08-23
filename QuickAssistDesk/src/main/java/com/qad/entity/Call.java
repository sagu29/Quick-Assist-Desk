package com.qad.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qad.entity.Operator;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Call {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer callId;
	
	@JsonFormat(pattern = "DD-MM-YYYY")
	@NotBlank(message = "{blank.invalid}")
	private LocalDate callDate; 
	
	@JsonFormat(pattern = "HH:mm:ss")
	@NotBlank(message = "{blank.invalid}")
	private LocalTime callDuration;
	
	@NotNull(message = "Mobile number field should not be empty")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Input a valid mobile number")
	@Column(name = "phoneNo")
	private String phoneNumber;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "issue_id")
	private Issue issue;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="operator_id")
	private Operator operator;
	
}
