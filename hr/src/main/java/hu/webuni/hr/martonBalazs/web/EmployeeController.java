package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.dto.VacationDto;
import hu.webuni.hr.martonBalazs.mapper.EmployeeMapper;
import hu.webuni.hr.martonBalazs.mapper.VacationMapper;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.EmployeeService;
import hu.webuni.hr.martonBalazs.service.VacationService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	VacationService vacationService;
	
	@Autowired
	VacationMapper vacationMapper;
	
	
//	@GetMapping
//	public List<EmployeeDto> getAll() {
//		return employeeMapper.employeesToDtos(employeeService.findAll());
//	}
	
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary, Pageable pageable){
		List<Employee> employees = null;
		if(minSalary == null) {
			employees = employeeService.findAll();
		} else {
			Page<Employee> page = employeeRepository.findBySalaryGreaterThan(minSalary, pageable);
			employees = page.getContent();
			System.out.println(page.getNumber());
			System.out.println(page.getNumberOfElements());
			System.out.println(page.getSize());
			System.out.println(page.getTotalElements());
			System.out.println(page.getTotalPages());
			System.out.println(page.isFirst());
		}
		return employeeMapper.employeesToDtos(employees);
	}
		
	
	@GetMapping("/{id}")
	public EmployeeDto getByid(@PathVariable long id) {
		Employee employee = employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return employeeMapper.employeeToDto(employee);
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable long id) {
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		employee.setId(id);
		try {
			EmployeeDto savedEmployeeDto = employeeMapper.employeeToDto(employeeService.update(employee));
			return ResponseEntity.ok(savedEmployeeDto);
		} catch (NoSuchElementException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
	
	@GetMapping("/position")
	public List<EmployeeDto> findEmployeesByPosition(@RequestParam String position) {
		List<Employee> employees = employeeRepository.findByPositionName(position);
		return employeeMapper.employeesToDtos(employees);
	}
	
	@GetMapping("/name")
	public List<EmployeeDto> findEmployeesByStartName(@RequestParam String name) {
		List<Employee> employees = employeeRepository.findByNameStartingWithIgnoreCase(name);
		return employeeMapper.employeesToDtos(employees);
	}
	
	@GetMapping("/start-date")
	public List<EmployeeDto> findEmployeesByDate(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
		List<Employee> employees = employeeRepository.findByStartDateBetween(start, end);
		return employeeMapper.employeesToDtos(employees);
	}
	

	@PostMapping("/{id}/vacations")
	public VacationDto createVacationForEmployee(@PathVariable long id, @RequestBody @Valid VacationDto vacationDto) {
		return vacationMapper.vacationToDto(vacationService.createForEmpoyee(id, vacationMapper.dtoToVacation(vacationDto)));
	}
}