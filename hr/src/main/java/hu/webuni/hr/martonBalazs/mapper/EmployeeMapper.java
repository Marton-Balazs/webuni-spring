package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> dtos);
	
	@Mapping(target = "position", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	
	@Mapping(target = "position.name", source = "position")
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	
}
