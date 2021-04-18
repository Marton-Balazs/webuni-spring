package hu.webuni.hr.martonBalazs.web;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.mapper.CompanyMapper;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.service.CompanyService;

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

	@GetMapping("/{id}")
	public CompanyDto getByid(@PathVariable long id) {
		return companyMapper.companyToDto(companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
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
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
}
