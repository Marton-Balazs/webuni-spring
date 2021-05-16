package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.Repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.PositionRepository;
import hu.webuni.hr.martonBalazs.Repository.VacationRepository;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.model.Position;
import hu.webuni.hr.martonBalazs.model.PositionDetailsByCompany;
import hu.webuni.hr.martonBalazs.model.Qualification;
import hu.webuni.hr.martonBalazs.model.Vacation;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class InitDbService {
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	@Autowired
	PasswordEncoder PasswordEncoder;
	
	@Transactional
	public void initDb() {
		Position developer = positionRepository.save(new Position("developer", Qualification.UNIVERSITY));
		Position tester = positionRepository.save(new Position("tester", Qualification.COLLEGE));
		Position assistant = positionRepository.save(new Position("assistant", Qualification.HIGH_SCHOOL));
		Position trainee = positionRepository.save(new Position("trainee", Qualification.COLLEGE));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(null, "name1Boss", 50000, LocalDateTime.now(), null, "uname1", PasswordEncoder.encode("pass"), Set.of("admin", "user")));
		newEmployee1.setPosition(developer);
		
		Employee newEmployee2 = employeeRepository.save(new Employee(null, "Name2", 200000, LocalDateTime.now(), null, "uname2", PasswordEncoder.encode("pass"), Set.of("admin", "user")));
		newEmployee2.setPosition(tester);
		
		Employee newEmployee3 = employeeRepository.save(new Employee(null, "Name3", 100000, LocalDateTime.now(), newEmployee2, "uname3", PasswordEncoder.encode("pass"), Set.of("admin", "user")));
		newEmployee3.setPosition(assistant);
		
		Employee newEmployee4 = employeeRepository.save(new Employee(null, "Name4", 500000, LocalDateTime.now(), newEmployee1, "uname4", PasswordEncoder.encode("pass"), Set.of("admin", "user")));
		newEmployee4.setPosition(trainee);
		
		Company newCompany = companyRepository.save(new Company(null, 001, "TestCompany1", "adress1", null));
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee4);
		
		Company newCompany2 = companyRepository.save(new Company(null, 002, "TestCompany2", "adress2", null));
		newCompany2.addEmployee(newEmployee2);
		newCompany2.addEmployee(newEmployee3);
		
		Vacation vacation = vacationRepository.save(new Vacation(LocalDate.now(), LocalDate.now(), newEmployee4, LocalDateTime.now()));
		
		PositionDetailsByCompany pd = new PositionDetailsByCompany();
		pd.setCompany(newCompany);
		pd.setMinSalary(250000);
		pd.setPosition(developer);
		positionDetailsByCompanyRepository.save(pd);
		
		PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
		pd2.setCompany(newCompany);
		pd2.setMinSalary(200000);
		pd2.setPosition(tester);
		positionDetailsByCompanyRepository.save(pd2);
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
