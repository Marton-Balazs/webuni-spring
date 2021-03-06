package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.model.Company;
import hu.webuni.hr.martonBalazs.model.Employee;

//rátszi a component intgrációt, azaz tudjuk injektálni a mapperünket
@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto companyToDto(Company company);
	
	@Mapping(target = "employees", ignore = true) 
	@Named("summary")
	CompanyDto companySummaryToDto(Company company);
	
	List<CompanyDto> companiesToDtos(List<Company> companies);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companySummariesToDtos(List<Company> companies);

	Company dtoToCompany(CompanyDto companyDto);
	
	@Mapping(target = "position", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	
	@Mapping(target = "position.name", source = "position")
	Employee dtoToEmployee(EmployeeDto employeeDto);

}
