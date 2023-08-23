package com.qad.Entity;

import java.time.LocalDateTime;

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
public class CurrentOperatorSession {
	
	@Id
	@Column(unique = true)
	private Integer userId;
	
	private String token;
	
	private LocalDateTime localDateTime;

}