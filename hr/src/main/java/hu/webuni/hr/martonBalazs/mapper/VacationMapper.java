package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;
import hu.webuni.hr.martonBalazs.dto.VacationDto;
import hu.webuni.hr.martonBalazs.model.Employee;
import hu.webuni.hr.martonBalazs.model.Vacation;

@Mapper(componentModel = "spring")
public interface VacationMapper {
	
	VacationDto vacationToDto(Vacation vacation);

	Vacation dtoToVacation(VacationDto vacationDto);
	
	@Mapping(target = "position", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(target = "position.name", source = "position")
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<VacationDto> vacationsToDtos(List<Vacation> vacation);
	
	List<Vacation> dtosToVacations(List<VacationDto> vacation);

}
