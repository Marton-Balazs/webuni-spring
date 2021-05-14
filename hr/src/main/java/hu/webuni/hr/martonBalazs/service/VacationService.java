package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		if (vacation.isAccepted() == null) {
			this.delete(id);
		}
	}

	@Transactional
	public void modifyVacation(long id, VacationDto vacationDto) {
		Vacation vacation = this.findById(id).orElseThrow();
		if (vacation.isAccepted() == null) {
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
}
