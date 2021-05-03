package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.Repository.PositionRepository;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.model.Position;
import hu.webuni.hr.martonBalazs.model.Qualification;

@Service
public class InitDbService {
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Transactional
	public void initDb() {
		Position developer = positionRepository.save(new Position("developer", Qualification.UNIVERSITY, 500000));
		Position tester = positionRepository.save(new Position("tester", Qualification.COLLEGE, 200000));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(null, "Name1", 300000, LocalDateTime.now()));
		newEmployee1.setPosition(developer);
		
		Employee newEmployee2 = employeeRepository.save(new Employee(null, "Name2", 200000, LocalDateTime.now()));
		newEmployee1.setPosition(tester);
		
		Company newCompany = companyRepository.save(new Company(null, 001, "TestCompany1", "adress1", null));
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);
	}
	
	public void clearDb() {
		List<Company> companies = companyRepository.findAll();
		companyRepository.deleteAll(companies);
		
		List<Employee> employees = employeeRepository.findAll();
		employeeRepository.deleteAll(employees);
	}
	
//	public void insertTestData() {
//		Employee employee = new Employee(null, "TestName1", 100, LocalDateTime.parse("2018-07-22T09:00:00"));
//		employeeRepository.save(employee);
//		
//		List<Employee> employees = new ArrayList<>();
//		employees.add(employee);
//		
//		Company company = new Company(null, 1, "ComapanyName1", "1111 Bp szevasz utca 1", employees);
//		companyRepository.save(company);
//	}
}
