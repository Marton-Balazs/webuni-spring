package hu.webuni.hr.martonBalazs.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	//paraméterül kap egy alkalmazottat és ennak az alkalmazottnak az új havi fizetését beállítja
	public int getNewSalary(Employee employee) {
		return (int)(employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee)));
	}

}
