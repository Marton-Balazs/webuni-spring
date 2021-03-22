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
		long months = java.time.temporal.ChronoUnit.MONTHS.between(start , stop);
			
//		    if (months >= limit10) return percent10;
//		    else if (months >= limit5 && months < limit10) return percent5;
//		    else if (months >= limit2 && months < limit5) return percent2;
//		    else return percent0;
		
		if (months >= config.getEmployee().getSpecial().getLimitVeryGood()) return config.getEmployee().getSpecial().getPercentVeryGood();
		else if (months >= config.getEmployee().getSpecial().getLimitGood() && months < config.getEmployee().getSpecial().getLimitVeryGood()) return config.getEmployee().getSpecial().getPercentGood();
		else if (months >= config.getEmployee().getSpecial().getLimitAvarage() && months < config.getEmployee().getSpecial().getLimitGood()) return (config.getEmployee().getSpecial().getPercentAvarage());
		else return config.getEmployee().getSpecial().getPercentNothing();
	}
}
