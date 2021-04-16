package hu.webuni.hr.martonBalazs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
	
	public DefaultEmployeeService(EmployeeRepository employeeRepository) {
		super(employeeRepository);
	}

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
