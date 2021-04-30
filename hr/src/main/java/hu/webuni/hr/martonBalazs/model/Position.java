package hu.webuni.hr.martonBalazs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Position {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Qualification qualification;
	private int minSaraly;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public int getMinSaraly() {
		return minSaraly;
	}
	public void setMinSaraly(int minSaraly) {
		this.minSaraly = minSaraly;
	}

}
