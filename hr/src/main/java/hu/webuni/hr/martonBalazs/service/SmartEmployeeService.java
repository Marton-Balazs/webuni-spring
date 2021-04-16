package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.config.HrConfigProperties;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {
	

//	@Value("${hr.employee.special.limitVeryGood}")
//	private int limitVeryGood;
//	
//	@Value("${hr.employee.special.limitGood}")
//	private int limitGood;
//	
//	@Value("${hr.employee.special.limitAvarage}")
//	private double limitAvarage;
//	
//	@Value("${hr.employee.special.PercentVeryGood}")
//	private int percentVeryGood;
//	
//	@Value("${hr.employee.special.percentGood}")
//	private int percentGood;
//	
//	@Value("${hr.employee.special.percentAvarage}")
//	private int percentAvarage;
//	
//	@Value("${hr.employee.special.percentNothing}")
//	private int percentNothing;
	


	public SmartEmployeeService(EmployeeRepository employeeRepository) {
		super(employeeRepository);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		LocalDateTime start = employee.getStartDate();
		LocalDateTime stop = LocalDateTime.now();
		long months = java.time.temporal.ChronoUnit.MONTHS.between(start , stop);
			
//		    if (months >= limitVeryGood) return percentVeryGood;
//		    else if (months >= limitGood && months < limitVeryGood) return percentGood;
//		    else if (months >= limitAvarage && months < limitGood) return percentAvarage;
//		    else return percentNothing;
		
		if (months >= config.getEmployee().getSpecial().getLimitVeryGood()) return (int) config.getEmployee().getSpecial().getPercentVeryGood();
		else if (months >= config.getEmployee().getSpecial().getLimitGood() && months < config.getEmployee().getSpecial().getLimitVeryGood()) return (int) config.getEmployee().getSpecial().getPercentGood();
		else if (months >= config.getEmployee().getSpecial().getLimitAvarage() && months < config.getEmployee().getSpecial().getLimitGood()) return (int) (config.getEmployee().getSpecial().getPercentAvarage());
		else return (int) config.getEmployee().getSpecial().getPercentNothing();
	}
}
