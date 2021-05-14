package hu.webuni.hr.martonBalazs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.martonBalazs.web.View;

//Ha valamit még be kell tölteni, akkor itt kell felvenni plusz atributomként
@NamedEntityGraph(name = "Company.full", 
	attributeNodes = @NamedAttributeNode(value = "employees", subgraph = "employeePosition"), 
	subgraphs = {
		@NamedSubgraph(name = "employeePosition", attributeNodes = { @NamedAttributeNode("position") }) })
@Entity
public class Company {

	@JsonView(View.OnlyCompany.class)
	@Id
	@GeneratedValue
	private Long id;

	@JsonView(View.OnlyCompany.class)
	private int registrationNumber;
	@JsonView(View.OnlyCompany.class)
	private String name;
	@JsonView(View.OnlyCompany.class)
	private String address;

	// ha ennek a companynak a listájába berakok egy employeet, akkor magától ennek
	// az employeenak a companyje nem fog átállni erre a comapanyre. Ezért kell ide
	// az addEmployee metódus
	@OneToMany(mappedBy = "company")
	List<Employee> employees = new ArrayList<>();

	@ManyToOne
	private CompanyType companyType;

//	@NamedEntityGraph(
//			name = "movieWithActorsAndAwards", 
//			attributeNodes = {
//					@NamedAttributeNode( value = "movieActors", subgraph = "movieActorsGraph")
//			},
//			subgraphs = {
//					@NamedSubgraph(name = "movieActorsGraph", attributeNodes = {
//							@NamedAttributeNode("movieActorAwards")
//					})
//			}
//	)
	public Company() {

	}

	public Company(Long id, int registrationNumber, String name, String adress, List<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = adress;
		this.employees = employees;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	// Így két irányból rendbe raktuk a kapcsolatot
	public void addEmployee(Employee employee) {
		if (this.employees == null) {
			this.employees = new ArrayList<>();
		}
		this.employees.add(employee);
		employee.setCompany(this);

	}

}
