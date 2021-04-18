package hu.webuni.hr.martonBalazs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
