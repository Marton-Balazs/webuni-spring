package hu.webuni.hr.martonBalazs.dto;

import java.util.ArrayList;

public class CompanyDto {
	
	private Long id;
	private int registrationNumber;
	private String name;
	private String adress;
	ArrayList<EmployeeDto> employees = new ArrayList<>();
	
	public CompanyDto() {
		
	}

	public CompanyDto(Long id, int registrationNumber, String name, String adress, ArrayList<EmployeeDto> employees) {
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

	public ArrayList<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<EmployeeDto> employees) {
		this.employees = employees;
	}

}
