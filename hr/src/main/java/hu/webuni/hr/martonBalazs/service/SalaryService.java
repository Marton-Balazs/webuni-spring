package hu.webuni.hr.martonBalazs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.Repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.martonBalazs.Repository.PositionRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepositiry;
	private EmployeeRepository employeeRepository;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public PositionRepository getPositionRepository() {
		return positionRepository;
	}

	public void setPositionRepository(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}

	public PositionDetailsByCompanyRepository getPositionDetailsByCompanyRepositiry() {
		return positionDetailsByCompanyRepositiry;
	}

	public void setPositionDetailsByCompanyRepositiry(
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepositiry) {
		this.positionDetailsByCompanyRepositiry = positionDetailsByCompanyRepositiry;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public int getNewSalary(Employee employee) {
		return (int)(employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee)));
	}
	
	
	
//	@Transactional
//	public void raiseMinimumSalary(String positionName, int minSalary) {
//		positionRepository.findByName(positionName)
//		.forEach(p -> {
//			p.setMinSaraly(minSalary);
//			p.getEmployees().forEach(e -> {
//				if (e.getSalary() < minSalary) {
//					e.setSalary(minSalary);
//			}
//		});
//		});
//	}
	
	@Transactional
	public void raiseMinimalSalary(String positionName, int minSalary, long companyId) {
		positionDetailsByCompanyRepositiry.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd ->{
			pd.setMinSalary(minSalary);
		});
		
		employeeRepository.updateSalaries(positionName, minSalary, companyId);
	}
}
