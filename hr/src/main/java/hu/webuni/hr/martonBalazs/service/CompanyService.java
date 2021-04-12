package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class CompanyService {
	
	private Map<Long, Company> companies = new HashMap<>();
	ArrayList<Employee> employees1 = new ArrayList<>();
	ArrayList<Employee> employees2 = new ArrayList<>();
	EmployeeService employeeService;
	
	{
		companies.put(1L, new Company(1L, 100000, "T-Systems", "1118 Bp Neumann János u 1/b", employees1));
		
		employees1.add(new Employee(1L, "Károly", "musician", 50, LocalDateTime.parse("2015-03-11T10:00:00")));
		employees1.add(new Employee(2L, "Vazul", "kukás", 100, LocalDateTime.parse("1999-03-11T10:00:00")));
	
		companies.put(2L, new Company(2L, 100001, "Unisys", "2154 Bp Váci út 1-3", employees2));
		
		employees2.add(new Employee(1L, "Béla", "Büfés", 50, LocalDateTime.parse("2003-04-11T10:00:00")));
		employees2.add(new Employee(2L, "János", "nobody", 100, LocalDateTime.parse("1986-03-11T10:00:00")));
		
	}
	
	public Company save(Company company) {
		checkUniqueId(company.getId());
		companies.put(company.getId(), company);
		return company;
	}
	
	public Company update(Company company) {
		companies.put(company.getId(), company);
		return company;
	}
	
	public List<Company> findAll() {
		return new ArrayList<>(companies.values());
	}
	
	public Company findById(long id) {
		return companies.get(id);
	}
	
	public void delete(long id) {
		companies.remove(id);
	}
	
	private void checkUniqueId(long id) {
		Optional<Company> companyWithSameID = companies.values().stream().filter(a -> a.getId().equals(id)).findAny();
		if (companyWithSameID.isPresent()) {
			throw new NonUniqueIDException(id);
		}
	}

}
