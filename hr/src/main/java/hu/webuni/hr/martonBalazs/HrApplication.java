package hu.webuni.hr.martonBalazs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.EmployeeService;
import hu.webuni.hr.martonBalazs.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	
	
	@Autowired
	SalaryService salaryService;
	
	//Employee e1 = new Employee((long) 1, "Balazs", "developer", dateTime);

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(salaryService.getNewSalary(e1));
	}

}
