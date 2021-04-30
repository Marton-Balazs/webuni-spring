package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class InitDbService {
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	public void clearDb() {
		List<Company> companies = companyRepository.findAll();
		companyRepository.deleteAll(companies);
		
		List<Employee> employees = employeeRepository.findAll();
		employeeRepository.deleteAll(employees);
	}
	
	public void insertTestData() {
		Employee employee = new Employee(null, "TestName1", 100, LocalDateTime.parse("2018-07-22T09:00:00"));
		employeeRepository.save(employee);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		Company company = new Company(null, 1, "ComapanyName1", "1111 Bp szevasz utca 1", employees);
		companyRepository.save(company);
	}
}
