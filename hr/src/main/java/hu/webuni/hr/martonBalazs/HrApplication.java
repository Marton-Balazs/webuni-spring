package hu.webuni.hr.martonBalazs;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.DefaultEmployeeService;
import hu.webuni.hr.martonBalazs.service.EmployeeService;
import hu.webuni.hr.martonBalazs.service.SalaryService;
import hu.webuni.hr.martonBalazs.service.SmartEmployeeService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	
	
	@Autowired
	SalaryService salaryService;
	
	
	Employee e1 = new Employee((long) 1, "Balazs", "developer", 1000, LocalDateTime.parse("2013-03-11T10:00:00"));
	Employee e2 = new Employee((long)2, "Kata", "tester", 500, LocalDateTime.parse("2010-01-01T09:00:00"));
	Employee e3 = new Employee((long)2, "Kenny", "pet", 100, LocalDateTime.parse("2018-01-01T09:00:00"));
	Employee e4 = new Employee((long)2, "Mazsi", "pet2", 100, LocalDateTime.parse("2021-01-01T09:00:00"));
	Employee e5 = new Employee((long)2, "Széli", "pet3", 100, LocalDateTime.parse("2011-03-20T09:00:00"));

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(salaryService.getNewSalary(e1));
		System.out.println(salaryService.getNewSalary(e2));
		System.out.println(salaryService.getNewSalary(e3));
		System.out.println(salaryService.getNewSalary(e4));
		System.out.println(salaryService.getNewSalary(e5));
	}
}
