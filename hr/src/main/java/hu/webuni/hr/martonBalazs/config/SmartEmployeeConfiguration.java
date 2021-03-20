package hu.webuni.hr.martonBalazs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.martonBalazs.service.DefaultEmployeeService;
import hu.webuni.hr.martonBalazs.service.EmployeeService;
import hu.webuni.hr.martonBalazs.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployeeConfiguration {
	
	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}

}
