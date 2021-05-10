package hu.webuni.hr.martonBalazs.dto;

import java.time.LocalDateTime;

import hu.webuni.hr.martonBalazs.model.Employee;

public class VacationDto {
	
	private long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Employee employee;
	private LocalDateTime createdOn;
	private Boolean accepted;
	
	public VacationDto() {
	}

	public VacationDto(LocalDateTime startDate, LocalDateTime endDate, Employee employee, LocalDateTime createdOn) {
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
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
