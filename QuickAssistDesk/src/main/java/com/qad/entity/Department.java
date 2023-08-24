package com.qad.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Department {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int departmentId;
	    @NotNull(message = "Department_name should not be empty!")
	    private String departmentName;

	    @JsonIgnore
	    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Operator> operators;

		public Department(@NotNull(message = "Department_name should not be empty!") String departmentName) {
			super();
			this.departmentName = departmentName;
		}
}
