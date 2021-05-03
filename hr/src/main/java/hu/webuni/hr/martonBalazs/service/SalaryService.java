package hu.webuni.hr.martonBalazs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.martonBalazs.Repository.PositionRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	private PositionRepository positionRepository;

	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
	}

	public int getNewSalary(Employee employee) {
		return (int)(employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee)));
	}
	
	@Transactional
	public void raiseMinimumSalary(String positionName, int minSalary) {
		positionRepository.findByName(positionName)
		.forEach(p -> {
			p.setMinSaraly(minSalary);
			p.getEmployees().forEach(e -> {
				if (e.getSalary() < minSalary) {
					e.setSalary(minSalary);
			}
		});
		});
	}
}
