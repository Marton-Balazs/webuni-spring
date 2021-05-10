package hu.webuni.hr.martonBalazs.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.martonBalazs.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByPositionName(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
	
	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageAble);
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN ("
			+ "SELECT e2.id "
			+ "FROM Employee e2 "
			+ "WHERE e2.position.name=:positionName "
			+ "AND e2.salary< :minSalary "
			+ "AND e2.company.id=:companyId)")
	int updateSalaries(String positionName, int minSalary, long companyId);
	
}
