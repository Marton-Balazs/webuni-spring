package hu.webuni.hr.martonBalazs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.mapper.CompanyMapper;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.service.CompanyService;
import hu.webuni.hr.martonBalazs.service.EmployeeService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@GetMapping
	public List<CompanyDto> getAll() {
		return companyMapper.companiesToDtos(companyService.findAll());
	}
	
//	@GetMapping
//	@JsonView(View.OnlyCompany.class)
//	public List<CompanyDto> getOnlyCompany(@RequestParam(required = false) Boolean full) {
//		return new ArrayList<> (companies.values());
//	}
	
	@GetMapping("/{id}")
	public CompanyDto getByid(@PathVariable long id) {
		Company company = companyService.findById(id);
		if (company != null) {
			return companyMapper.companiesToDto(company);
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
		//Dtoval szeretnénk Company-t csinálni, ehhez kell a mapper
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companiesToDto(company);
	}
	
	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@RequestBody CompanyDto companyDto, @PathVariable long id) {
		Company company = companyService.update(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companiesToDto(company);
		
		/*
		if (!companies.containsKey(id)) {
		return ResponseEntity.notFound().build();
		}
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
		*/
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
//	//meglévő céghez új alkalmazott vehető fel
//	@PostMapping("/{id}")
//	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto ) {
//		//id alapján kivesszem a companyDto-t a mapből:
//		CompanyDto companyDto = companies.get(id);
//		companyDto.getEmployees().add(employeeDto);
//		return companyDto;
//	}
//	
//	//cég alkalmazottai közül id alapján törölhető egy
//	@DeleteMapping("/{id}/employee/{employee}")
//	public ResponseEntity<CompanyDto> deleteEmployeeFromCompany(@PathVariable long id, @PathVariable long employee) {
//		//id alapján kivesszem a companyDto-t a mapből:
//		CompanyDto companyDto = companies.get(id);
//		EmployeeDto employeeDto = companyDto.getEmployees().stream().filter(e -> employee == e.getId()).findFirst().orElse(null);
//		companyDto.getEmployees().remove(employeeDto);
//		return ResponseEntity.noContent().build();
//	}
//	
//	//az alkalmazotti lista lecserélhető egy mésikra
//	@PutMapping("/{id}/employees")
//	public CompanyDto replaceEmoloyeesList(@PathVariable long id, @RequestBody ArrayList<EmployeeDto> employees) {
//		//id alapján kivesszem a companyDto-t a mapből:
//		CompanyDto companyDto = companies.get(id);
//		companyDto.getEmployees().clear();
//		companyDto.getEmployees().addAll(employees);
//		return companyDto;
//	}
//	
//	@PostMapping("/payRaise")
//	public int getPayRaise(@RequestBody Employee employee) {
//		return employeeService.getPayRaisePercent(employee);
//	}

}
