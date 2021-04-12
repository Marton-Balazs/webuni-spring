package hu.webuni.hr.martonBalazs.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.martonBalazs.web.View;


public class Company {
	
	@JsonView(View.OnlyCompany.class)
	private Long id;
	@JsonView(View.OnlyCompany.class)
	private int registrationNumber;
	@JsonView(View.OnlyCompany.class)
	private String name;
	@JsonView(View.OnlyCompany.class)
	private String adress;
	
	ArrayList<Employee> employees = new ArrayList<>();
	
	public Company() {
		
	}

	public Company(Long id, int registrationNumber, String name, String adress, ArrayList<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.adress = adress;
		this.employees = employees;
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
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

}
