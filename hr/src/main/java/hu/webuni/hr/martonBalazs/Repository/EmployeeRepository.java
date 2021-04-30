package hu.webuni.hr.martonBalazs.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByPositionName(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
	
	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageAble);
	
}
