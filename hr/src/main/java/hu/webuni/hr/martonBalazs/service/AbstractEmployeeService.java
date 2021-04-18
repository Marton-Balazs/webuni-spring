package hu.webuni.hr.martonBalazs.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {
	EmployeeRepository employeeRepository;

	public AbstractEmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		if (employeeRepository.existsById(employee.getId())) {
			return employeeRepository.save(employee);
		} else {
			throw new NoSuchElementException();
		}
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
}
