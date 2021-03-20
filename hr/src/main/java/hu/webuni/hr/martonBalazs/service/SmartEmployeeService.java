package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.config.HrConfigProperties;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{
	
//	@Value("${hr.employee.special.limit10}")
//	private int limit10;
//	
//	@Value("${hr.employee.special.limit5}")
//	private int limit5;
//	
//	@Value("${hr.employee.special.limit2}")
//	private double limit2;
//	
//	@Value("${hr.employee.special.percent10}")
//	private int percent10;
//	
//	@Value("${hr.employee.special.percent5}")
//	private int percent5;
//	
//	@Value("${hr.employee.special.percent2}")
//	private int percent2;
//	
//	@Value("${hr.employee.special.percent0}")
//	private int percent0;
	
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		LocalDateTime start = employee.getStartDate();
		LocalDateTime stop = LocalDateTime.now();
		long years = java.time.temporal.ChronoUnit.YEARS.between(start , stop);
			
//		    if (years >= limit10) return percent10;
//		    else if (years >= limit5 && years < limit10) return percent5;
//		    else if (years >= limit2 && years < limit5) return percent2;
//		    else return percent0;
		
		if (years >= config.getEmployee().getSpecial().getLimit10()) return config.getEmployee().getSpecial().getPercent10();
		else if (years >= config.getEmployee().getSpecial().getLimit5() && years < config.getEmployee().getSpecial().getLimit10()) return config.getEmployee().getSpecial().getPercent5();
		else if (years >= config.getEmployee().getSpecial().getLimit2() && years < config.getEmployee().getSpecial().getLimit5()) return config.getEmployee().getSpecial().getPercent2();
		else return config.getEmployee().getSpecial().getPercent0();
	}
}
