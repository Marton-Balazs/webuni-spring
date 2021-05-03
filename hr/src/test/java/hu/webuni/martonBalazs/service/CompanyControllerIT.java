package hu.webuni.martonBalazs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.service.CompanyService;


@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyControllerIT {
	
	@Autowired
	CompanyService airportService;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

}
