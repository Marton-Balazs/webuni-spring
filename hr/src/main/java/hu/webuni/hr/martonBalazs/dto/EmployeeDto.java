package hu.webuni.hr.martonBalazs.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import hu.webuni.hr.martonBalazs.model.Vacation;

public class EmployeeDto {
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Position is mandatory")
	private String position;
	@Positive(message = "Salary must be positive")
	private int salary;
	@Past(message = "Entry date can not be in the future")
	private LocalDateTime startDate;
	
	
	public EmployeeDto() {
		
	}

	public EmployeeDto(Long id, String name, String position, int salary, LocalDateTime startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
}