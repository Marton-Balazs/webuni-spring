package hu.webuni.hr.martonBalazs.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	

	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	public Company update(Company company) {
		if (companyRepository.existsById(company.getId())) {
			return companyRepository.save(company);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}
	
	public void delete(long id) {
		companyRepository.deleteById(id);
	}
	
	public Company addEmployee(long id, Employee employee) {
		//a .get()-el kérem el az optionalbe csomagolt cuccot.
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}

	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}

	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).get();
		//company.getEmployees().stream().forEach(e -> {e.setCompany(null);});
		company.getEmployees().clear();
		
		for(Employee emp : employees) {
			company.addEmployee(emp);
		}
		
		companyRepository.save(company);
		
		return company;
	}
}
