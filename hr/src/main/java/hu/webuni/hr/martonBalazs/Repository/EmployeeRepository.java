package hu.webuni.hr.martonBalazs.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartingWith(String name);
	
	List<Employee> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
	
}
