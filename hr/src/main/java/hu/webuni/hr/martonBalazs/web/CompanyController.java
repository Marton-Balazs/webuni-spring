package hu.webuni.hr.martonBalazs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.mapper.CompanyMapper;
import hu.webuni.hr.martonBalazs.mapper.EmployeeMapper;
import hu.webuni.hr.martonBalazs.model.AverageSalaryByPosition;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
//	@GetMapping
//	public List<CompanyDto> getAll() {
//		return companyMapper.companiesToDtos(companyService.findAll());
//	}
	
//	@GetMapping
//	public List<CompanyDto> getCompanys(@RequestParam(required = false) Boolean full) {
//		List<Company> companies = companyService.findAll();
//		return full == null || full == false ? companyMapper.companySummariesToDtos(companies) : companyMapper.companiesToDtos(companies);
//	}
	
	//open in view false és queryt írtunk a companyRepoban:
	@GetMapping
	public List<CompanyDto> getCompanys(@RequestParam(required = false) Boolean full) {
		List<Company> companies = null;
		boolean notFull = (full == null || !full);
		if (notFull) {
			companies = companyService.findAll();
			return companyMapper.companySummariesToDtos(companies);
		} else {
			companies = companyRepository.findAllWithEmployees();
			return companyMapper.companiesToDtos(companies);
		}
	}

//	@GetMapping("/{id}")
//	public CompanyDto getByid(@PathVariable long id) {
//		return companyMapper.companyToDto(companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
//	}
	
	@GetMapping("/{id}")
	public CompanyDto getByid(@PathVariable long id,@RequestParam(required = false) Boolean full) {
		Company company = companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return full == null || full == false ? companyMapper.companySummaryToDto(company) : companyMapper.companyToDto(company);
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}
	
	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@RequestBody CompanyDto companyDto, @PathVariable long id) {
		Company company = companyMapper.dtoToCompany(companyDto);
		company.setId(id);
		
		try {
			return companyMapper.companyToDto(companyService.update(company));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		try {
			return companyMapper.companyToDto(companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees) {
		try {
			return companyMapper.companyToDto(companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employees)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		try {
			return companyMapper.companyToDto(companyService.deleteEmployee(id, employeeId));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
	//CompanyRepositoríból hívom a lekérdezéseket:
	
	@GetMapping(params = "aboveSalary")
	public List<CompanyDto> getCompaniesAboveASalary(@RequestParam int aboveSalary, @RequestParam(required = false) String full) {
		List<Company> allCompanies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
		if (full == null || full.equals("false")) {
			return companyMapper.companySummariesToDtos(allCompanies);
		} else
			return companyMapper.companiesToDtos(allCompanies);
	}

	@GetMapping(params = "aboveEmployeeNumber")
	public List<CompanyDto> getCompaniesAboveEmployeeNumber(@RequestParam int aboveEmployeeNumber, @RequestParam(required = false) String full) {
		List<Company> filteredCompanies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeNumber);
		if (full == null || full.equals("false")) {
			return companyMapper.companySummariesToDtos(filteredCompanies);
		} else
			return companyMapper.companiesToDtos(filteredCompanies);
	}

	@GetMapping("/{id}/salaryStats")
	public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		return companyRepository.findAverageSalariesByPosition(id);
	}
	
}
