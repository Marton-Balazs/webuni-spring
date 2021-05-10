package hu.webuni.hr.martonBalazs.Repository;

import java.util.List;

import javax.persistence.NamedEntityGraph;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.martonBalazs.model.AverageSalaryByPosition;
import hu.webuni.hr.martonBalazs.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	//@Query("SELECT DISTINCT c FROM Company c LEFT JOIn FETCH c.employees") --> ez csökkenti a SELECT-ek számát, de ezt is kiváltjuk egy entityGraph-al. Viszont akkor kell a comapny osztáylra is a @NamedEntityGrap..
	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployees();
	
	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > : minSalary")
	public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);
	
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);
	
	//adott id-val cég alkalmazottainak átlagfizetése beosztás szerint csoportosítva, átlagfizetés szerint csökkkenő
	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary " 
			+ "FROM Company c "
			+ "JOIN c.employees e "
			+ "WHERE c.id=:companyId " 
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	

}