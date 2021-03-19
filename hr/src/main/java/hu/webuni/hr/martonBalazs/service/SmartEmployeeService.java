package hu.webuni.hr.martonBalazs.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.model.Employee;


public class SmartEmployeeService implements EmployeeService{

	@Override
	public int getPayRaisePercent(Employee employee) {
		//if(employee.getStartDate() >= 10) return 10;
		return 10;
	}

}
