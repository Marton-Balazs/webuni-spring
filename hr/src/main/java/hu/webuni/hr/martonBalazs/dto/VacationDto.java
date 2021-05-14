package hu.webuni.hr.martonBalazs.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VacationDto {
	
	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private EmployeeDto employee;
	private LocalDateTime createdOn;
	private Boolean accepted;
	
	public VacationDto() {
	}

	public VacationDto(LocalDate startDate, LocalDate endDate, EmployeeDto employee, LocalDateTime createdOn) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.createdOn = createdOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	public Boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	

}
