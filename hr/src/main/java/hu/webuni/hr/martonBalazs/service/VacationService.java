package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.Repository.VacationRepository;
import hu.webuni.hr.martonBalazs.dto.VacationDto;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.model.Vacation;

@Service
public class VacationService {
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public Vacation save(Vacation vacation) {
		return vacationRepository.save(vacation);
	}
	
	@Transactional
	public Vacation update(Vacation vacation) {
		if (vacationRepository.existsById(vacation.getId())) {
			return vacationRepository.save(vacation);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public List<Vacation> findAll() {
		return vacationRepository.findAll();
	}
	
	public Optional<Vacation> findById(long id) {
		return vacationRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id) {
		vacationRepository.deleteById(id);
	}
	
	@Transactional
	public void setVacationStatus(long id, String status) {
		Vacation vacation = this.findById(id).orElseThrow();
		vacation.setAccepted(status.equals("accept") ? true : false);
	}
	
	@Transactional
	public void deleteVacation(long id) {
		Vacation vacation = this.findById(id).orElseThrow();
		if (vacation.getAccepted() == null) {
			this.delete(id);
		}
	}

	@Transactional
	public void modifyVacation(long id, VacationDto vacationDto) {
		Vacation vacation = this.findById(id).orElseThrow();
		if (vacation.getAccepted() == null) {
			vacation.setStartDate(vacationDto.getStartDate());
			vacation.setEndDate(vacationDto.getEndDate());
		}
	}
	
	@Transactional
	public Vacation createForEmpoyee(long employeeId, Vacation vacation) {
		Employee employee = employeeRepository.findById(employeeId).get();
		vacation.setEmployee(employee);
		vacation.setCreatedOn(LocalDateTime.now());
		return vacationRepository.save(vacation);
	}
	
	public List<Vacation> findVacationsByExample(Vacation example) {
		Boolean accept = example.getAccepted();
		Employee employee = example.getEmployee();
		employee = example.getEmployee();
		String name = null;
		
		if(employee != null)
			name  = employee.getName();

		Specification<Vacation> spec = Specification.where(null);
		
		if (accept != null) {
			spec.and(VacationSpecifications.hasAccepted(accept));
		}
		
		if (StringUtils.hasText(name)) {
			spec.and(VacationSpecifications.hasName(name));
		}
		
		return vacationRepository.findAll(spec);
		
	}

}
