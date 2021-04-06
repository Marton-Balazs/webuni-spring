package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	ArrayList<EmployeeDto> employees1 = new ArrayList<>();
	ArrayList<EmployeeDto> employees2 = new ArrayList<>();
	
	{
		companies.put(1L, new CompanyDto(1L, 100000, "T-Systems", "1118 Bp Neumann János u 1/b", employees1));
		
		employees1.add(new EmployeeDto(1L, "Károly", "musician", 50, LocalDateTime.parse("2015-03-11T10:00:00")));
		employees1.add(new EmployeeDto(2L, "Vazul", "kukás", 100, LocalDateTime.parse("1999-03-11T10:00:00")));
	
		companies.put(2L, new CompanyDto(2L, 100001, "Unisys", "2154 Bp Váci út 1-3", employees2));
		
		employees2.add(new EmployeeDto(1L, "Béla", "Büfés", 50, LocalDateTime.parse("2003-04-11T10:00:00")));
		employees2.add(new EmployeeDto(2L, "János", "nobody", 100, LocalDateTime.parse("1986-03-11T10:00:00")));
		
	}
	
	@GetMapping(params="full=true")
	public List<CompanyDto> getAll() {
		return new ArrayList<> (companies.values());
	}
	
	@GetMapping
	@JsonView(View.OnlyCompany.class)
	public List<CompanyDto> getOnlyCompany(@RequestParam(required = false) Boolean full) {
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
	
	//meglévő céghez új alkalmazott vehető fel
	@PostMapping("/{id}")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto ) {
		//id alapján kivesszem a companyDto-t a mapből:
		CompanyDto companyDto = companies.get(id);
		companyDto.getEmployees().add(employeeDto);
		return companyDto;
	}
	
	//cég alkalmazottai közül id alapján törölhető egy
	@DeleteMapping("/{id}/employee/{employee}")
	public ResponseEntity<CompanyDto> deleteEmployeeFromCompany(@PathVariable long id, @PathVariable long employee) {
		//id alapján kivesszem a companyDto-t a mapből:
		CompanyDto companyDto = companies.get(id);
		EmployeeDto employeeDto = companyDto.getEmployees().stream().filter(e -> employee == e.getId()).findFirst().orElse(null);
		companyDto.getEmployees().remove(employeeDto);
		return ResponseEntity.noContent().build();
	}
	
	//az alkalmazotti lista lecserélhető egy mésikra
	@PutMapping("/{id}/employees")
	public CompanyDto replaceEmoloyeesList(@PathVariable long id, @RequestBody ArrayList<EmployeeDto> employees) {
		//id alapján kivesszem a companyDto-t a mapből:
		CompanyDto companyDto = companies.get(id);
		companyDto.getEmployees().clear();
		companyDto.getEmployees().addAll(employees);
		return companyDto;
	}

}
