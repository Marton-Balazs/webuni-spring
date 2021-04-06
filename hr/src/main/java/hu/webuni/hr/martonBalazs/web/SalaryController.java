package hu.webuni.hr.martonBalazs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.EmployeeService;

@RestController
@RequestMapping("api/raisedSalary")
public class SalaryController {
	
	@Autowired
	EmployeeService employeeService;
	
	//return the raised salary
	@GetMapping
	public int getEmployeePaymentRise(@RequestBody Employee employee ){
		return employeeService.getPayRaisePercent(employee);
	}
}
