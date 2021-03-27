package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class HrController {
	
	private Map<Long, EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L, new EmployeeDto(1L, "Balazs", "developer", 5000, LocalDateTime.parse("2000-03-11T10:00:00")));
		employees.put(2L, new EmployeeDto(2L, "Alajos", "intern", 120, LocalDateTime.parse("2020-01-04T09:00:00")));
	}
	
	
	@GetMapping
	public List<EmployeeDto> getAll() {
		return new ArrayList<> (employees.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getByid(@PathVariable long id) {
		EmployeeDto employeeDto = employees.get(id);
		if (employeeDto != null) {
			return ResponseEntity.ok(employeeDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable long id) {
		if (!employees.containsKey(id)) {
		return ResponseEntity.notFound().build();
		}
		employeeDto.setId(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employees.remove(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getHighSalary(@RequestParam(value = "salary", required = false, defaultValue = "100") int salary, long id) {
		EmployeeDto employeeDto = employees.get(id);
		if (salary != 0 && salary > 100) {
			return ResponseEntity.ok(employeeDto);
		}
		return ResponseEntity.notFound().build();
	}
}