package hu.webuni.hr.martonBalazs.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService{

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
