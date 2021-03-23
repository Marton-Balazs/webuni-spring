package hu.webuni.hr.martonBalazs;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	
	@Autowired
	SalaryService salaryService;
	
	Employee e1 = new Employee(1L, "Balazs", "developer", 1000, LocalDateTime.parse("2013-03-11T10:00:00")); 	//8év 3hónap 5% = 1050
	Employee e2 = new Employee(2L, "Kata", "tester", 500, LocalDateTime.parse("2010-01-01T09:00:00"));			//10év+ 10% = 550
	Employee e3 = new Employee(3L, "Kenny", "pet", 100, LocalDateTime.parse("2018-01-01T09:00:00"));			//3év 2hónap 2% = 102
	Employee e4 = new Employee(4L, "Mazsi", "pet2", 100, LocalDateTime.parse("2021-01-01T09:00:00"));			//< 2.5év 0% = 100
	Employee e5 = new Employee(5L, "Széli", "pet3", 100, LocalDateTime.parse("2011-03-20T09:00:00"));			//>10év 10% = 110
	Employee e6 = new Employee(5L, "Test", "test", 100, LocalDateTime.parse("2018-10-22T09:00:00"));			//2év 4hónap 0% = 100
	Employee e7 = new Employee(5L, "Test2", "test2", 100, LocalDateTime.parse("2018-07-22T09:00:00"));			//2év 7hónap 2% = 102

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
		System.out.println(salaryService.getNewSalary(e6));
		System.out.println(salaryService.getNewSalary(e7));
	}
}
