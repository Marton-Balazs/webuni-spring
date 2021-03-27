package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.model.Employee;

@Controller
public class EmployeeTLController {
	
	//private List<Employee> allEmployees = new ArrayList<>();
	private List<EmployeeDto> allEmployees = new ArrayList<>();
	
	{
		allEmployees.add(new EmployeeDto(1L, "Gyula", "Maintanance", 20, LocalDateTime.parse("2000-03-11T10:00:00")));
		allEmployees.add(new EmployeeDto(2L, "Erzsébet", "Scrum master", 800, LocalDateTime.parse("2020-03-11T10:00:00")));
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("pageTitle", "Employees");
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", allEmployees);
		//model.put("newEmployee", new Employee());
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(EmployeeDto employee) {
		allEmployees.add(employee);
		return "redirect:employees";
	}

}
