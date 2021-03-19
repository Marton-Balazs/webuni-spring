package hu.webuni.hr.martonBalazs.service;

import hu.webuni.hr.martonBalazs.model.Employee;

public interface EmployeeService {
	
	//megadja, hogy %-os fizetés emelésjár egy alkalmazottnak
	public int getPayRaisePercent(Employee employee);

}
