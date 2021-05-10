package hu.webuni.hr.martonBalazs.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Position;
import hu.webuni.hr.martonBalazs.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> {

	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);

}
