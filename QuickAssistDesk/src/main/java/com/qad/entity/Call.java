package com.qad.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Call {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int callId;

	private LocalDate callDate;
	private double callDuration;
	private String phoneNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operator_id")
	private Operator operator;

	public Call(LocalDate callDate, double callDuration, String phoneNumber, Operator operator) {
		super();
		this.callDate = callDate;
		this.callDuration = callDuration;
		this.phoneNumber = phoneNumber;
		this.operator = operator;
	}
}
