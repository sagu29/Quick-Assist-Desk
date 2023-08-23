package com.qad.entity;

import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loginId;

	@Column(unique = true)
	@NotNull(message = "username should not be empty!")

	private String username;

	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private UserType type;  // we can make it enum if required
	private boolean isActive; // we can make it enum if required


	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
	private Operator operator;

}
