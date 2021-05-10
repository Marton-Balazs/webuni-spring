package hu.webuni.hr.martonBalazs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Positive(message = "Salary must be positive")
	private int salary;
	@Past(message = "Entry date can not be in the future")
	private LocalDateTime startDate;
	
	//1 céghez több employee tartozhat.
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Position position;
	
	@ManyToOne
	private Employee supervisor;
	
	public Employee() {
	}

	public Employee(Long id, String name, int salary, LocalDateTime startDate, Employee supervisor) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
		this.supervisor = supervisor;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
