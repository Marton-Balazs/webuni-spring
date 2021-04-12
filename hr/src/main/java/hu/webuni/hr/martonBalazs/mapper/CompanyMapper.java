package hu.webuni.hr.martonBalazs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.martonBalazs.dto.CompanyDto;
import hu.webuni.hr.martonBalazs.model.Company;

//rátszi a component intgrációt, azaz tudjuk injektálni a mapperünket
@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companiesToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);

}
