package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.martonBalazs.dto.VacationDto;
import hu.webuni.hr.martonBalazs.model.Vacation;

@Mapper(componentModel = "spring")
public interface VacationMapper {
	
	VacationDto vacationToDto(Vacation vacation);
	
	Vacation dtoToVacation(VacationDto vacationDto);
	
	List<VacationDto> vacationsToDtos(List<Vacation> vacation);
	
	List<Vacation> dtosToVacations(List<VacationDto> vacation);

}
