package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {
	
	
	EmployeeRepository employeeRepository;

	public AbstractEmployeeService(EmployeeRepository employeeRepository) {
			this.employeeRepository = employeeRepository;
	}

//	private Map<Long, Employee> employees = new HashMap<>();
//	
//	{
//		employees.put(1L, new Employee(1L, "Balazs", "developer", 5000, LocalDateTime.parse("2000-03-11T10:00:00")));
//		employees.put(2L, new Employee(2L, "Alajos", "intern", 120, LocalDateTime.parse("2020-01-04T09:00:00")));
//		employees.put(3L, new Employee(3L, "Gyula", "recepcionist", 500, LocalDateTime.parse("2015-11-20T09:00:00")));
//		employees.put(4L, new Employee(4L, "Kata", "pepsi cola giver", 4500, LocalDateTime.parse("2005-10-10T09:00:00")));
//	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}
	
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
	
//	private void checkUniqueId(long id) {
//		Optional<Employee> employeeWithSameID = employees.values().stream().filter(a -> a.getId().equals(id)).findAny();
//		if (employeeWithSameID.isPresent()) {
//			throw new NonUniqueIDException(id);
//		}
//	}

}
