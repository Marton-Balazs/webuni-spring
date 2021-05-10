package hu.webuni.hr.martonBalazs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.martonBalazs.Repository.VacationRepository;
import hu.webuni.hr.martonBalazs.dto.VacationDto;
import hu.webuni.hr.martonBalazs.mapper.VacationMapper;
import hu.webuni.hr.martonBalazs.model.Vacation;
import hu.webuni.hr.martonBalazs.service.VacationService;

@RestController
@RequestMapping("/api/vacation")
public class VacationController {
	
	@Autowired
	VacationService vacationService;
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	VacationMapper vacationMapper;
	
	@PutMapping("/{id}/{status}")
	public void setVacationStatus(@PathVariable long id, @PathVariable String status) {
		vacationService.setVacationStatus(id, status);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVacation(@PathVariable long id) {
		vacationService.deleteVacation(id);
	}
	
	@PutMapping("/{id}")
	public void modifyVacation(@PathVariable long id, @RequestBody VacationDto vacationDto) {
		vacationService.modifyVacation(id, vacationDto);
	}
	
	@GetMapping
	public List<VacationDto> getVacations(Pageable pageable){
		List<Vacation> vacations = null;
			Page<Vacation> page = vacationRepository.findByVacations(pageable);
			vacations = page.getContent(); //ez adja vissza a tényleges listát
			System.out.println(page.getTotalPages());
			System.out.println(page.isFirst());
		
		return vacationMapper.vacationsToDtos(vacations);
	}

}
