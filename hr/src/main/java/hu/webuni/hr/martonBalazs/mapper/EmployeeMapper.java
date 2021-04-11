package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	EmployeeDto employeesToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);

}
