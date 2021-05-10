package hu.webuni.hr.martonBalazs.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.martonBalazs.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
	
	//@Query("SELECT DISTINCT c FROM Company c LEFT JOIn FETCH c.employees") --> ez csökkenti a SELECT-ek számát, de ezt is kiváltjuk egy entityGraph-al. Viszont akkor kell a comapny osztáylra is a @NamedEntityGrap..
//	@EntityGraph("Company.full")
//	@Query("SELECT c FROM Company c")
	
	@Query("SELECT v FROM Vacation v")
	Page<Vacation> findByVacations(Pageable pageable);
	
	List<Vacation> findAllByAccepted(Boolean accepted);

	

}