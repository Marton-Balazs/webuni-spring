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
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	
	{
		ArrayList<EmployeeDto> employees1 = new ArrayList<>();
		ArrayList<EmployeeDto> employees2 = new ArrayList<>();
		
		companies.put(1L, new CompanyDto(1L, 100000, "T-Systems", "cím", employees1));
		
		employees1.add(new EmployeeDto(1L, "Károly", "musician", 50, LocalDateTime.parse("2015-03-11T10:00:00")));
		employees1.add(new EmployeeDto(2L, "Vazul", "nobody", 100, LocalDateTime.parse("1999-03-11T10:00:00")));
	
		companies.put(2L, new CompanyDto(2L, 100001, "Unisys", "cím", employees2));
		
		employees2.add(new EmployeeDto(3L, "Béla", "musician", 50, LocalDateTime.parse("2003-04-11T10:00:00")));
		employees2.add(new EmployeeDto(4L, "János", "nobody", 100, LocalDateTime.parse("1986-03-11T10:00:00")));
		
		
	}
	
	
	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<> (companies.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getByid(@PathVariable long id) {
		CompanyDto company  = companies.get(id);
		if (company != null) {
			return ResponseEntity.ok(company);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@RequestBody CompanyDto companyDto, @PathVariable long id) {
		if (!companies.containsKey(id)) {
		return ResponseEntity.notFound().build();
		}
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	

}
