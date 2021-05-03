package hu.webuni.hr.martonBalazs.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.martonBalazs.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	
	public List<Position> findByName(String name);

}
