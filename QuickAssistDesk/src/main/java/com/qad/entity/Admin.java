package com.qad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Login{
	@Column(unique = true)
    private int adminId;
	@NotNull(message = "name should not be empty!")
    private String firstName;
    private String lastName;
    
    @Column(unique = true)
    @Email(message = "email should be formatted!")
    @NotNull(message = "email should not be empty!")
    private String email;
    @NotNull(message = "mobile should not be empty!")
    private String mobile;
    private String city;
    
    
}
