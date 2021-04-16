package hu.webuni.hr.martonBalazs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
