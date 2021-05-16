package hu.webuni.hr.martonBalazs.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.martonBalazs.model.Employee_;
import hu.webuni.hr.martonBalazs.model.Vacation;
import hu.webuni.hr.martonBalazs.model.Vacation_;

public class VacationSpecifications {
	
	public static Specification<Vacation> hasAccepted(Boolean accepted){
		return (root, cq, cb) -> cb.equal(root.get(Vacation_.accepted), accepted);
	}
	
	public static Specification<Vacation> hasName(String name){
		return (root, cq, cb) -> cb.like(root.get(Vacation_.employee).get(Employee_.name), name + "%");
	}
	
	public static Specification<Vacation> dateOfVacation(LocalDateTime createdOn) {
		LocalDateTime startOfDay = LocalDateTime.of(
				createdOn.toLocalDate(), LocalTime.of(0, 0));
		return (root, cq, cb) -> cb.between(root.get(Vacation_.createdOn), 
				startOfDay, startOfDay.plusDays(1));
	}

}