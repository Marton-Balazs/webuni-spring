package hu.webuni.hr.martonBalazs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vacation {
	
	@Id
	@GeneratedValue
	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	@ManyToOne
	private Employee employee;
	private LocalDateTime createdOn;
	private Boolean accepted;
	
	public Vacation() {
	}

	public Vacation(LocalDate startDate, LocalDate endDate, Employee employee, LocalDateTime createdOn) {
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
