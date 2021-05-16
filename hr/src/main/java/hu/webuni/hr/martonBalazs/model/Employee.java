package hu.webuni.hr.martonBalazs.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	private String username;
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	
	//1 céghez több employee tartozhat.
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Position position;
	
	@ManyToOne
	private Employee supervisor;
	
	public Employee() {
		
	}

	public Employee(Long id, String name, int salary, LocalDateTime startDate, Employee supervisor, String username, String password, Set<String> roles) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
		this.supervisor = supervisor;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
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
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
}