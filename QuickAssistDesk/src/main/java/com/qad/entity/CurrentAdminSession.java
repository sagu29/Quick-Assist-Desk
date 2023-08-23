package com.qad.entity;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@CrossOrigin(origins = "*")
public class CurrentAdminSession {
	
	@Id
	@Column(unique = true)
	private Integer userId;

	private String token;
	
	private LocalDateTime localDateTime;
}
