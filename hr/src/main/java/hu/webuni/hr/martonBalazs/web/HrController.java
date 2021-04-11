package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.mapper.EmployeeMapper;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.EmployeeService2;
import hu.webuni.hr.martonBalazs.service.NonUniqueIDException;

@RestController
@RequestMapping("/api/employees")
public class HrController {
	
	@Autowired
	EmployeeService2 employeeService2;
	
	@Autowired
	EmployeeMapper employeeMapper;
	

	
//	@GetMapping(params="minSalary")
//	public List<EmployeeDto> getMinSalary(@RequestParam int minSalary) {
//		ArrayList<EmployeeDto> specialEmployee = new ArrayList<>();
//		for(Entry<Long, EmployeeDto> entry : employees.entrySet()) {
//		    EmployeeDto edto = entry.getValue();
//		    if (edto.getSalary() > minSalary && minSalary > 0) {
//		    	specialEmployee.add(edto);
//			}
//		}
//		return specialEmployee;
//	}
	
	
	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeesToDtos(employeeService2.findAll());
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getByid(@PathVariable long id) {
		Employee employee = employeeService2.findById(id);
		if (employee != null) {
			return employeeMapper.employeesToDto(employee);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService2.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeesToDto(employee);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable long id) {
//		if (!employees.containsKey(id)) {
//		return ResponseEntity.notFound().build();
//		}
//		checkUniqueId(employeeDto.getId());
//		employeeDto.setId(id);
//		employees.put(id, employeeDto);
//		return ResponseEntity.ok(employeeDto);
//	}


//	@DeleteMapping("/{id}")
//	public void deleteEmployee(@PathVariable long id) {
//		employees.remove(id);
//	}

}