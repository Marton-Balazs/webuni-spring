package hu.webuni.hr.martonBalazs.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Position {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Qualification qualification;
	private int minSaraly;
	
	
	//position irányából el kell érnem a hozzá tartozó employeekat is, ezért fel kjell venni az irányát ennek a kapcsolatnak is:
	@OneToMany(mappedBy = "position")
	private List<Employee> employees;
	
	public Position() {
	}

	public Position(String name, Qualification qualification, int minSaraly) {
		this.name = name;
		this.qualification = qualification;
		this.minSaraly = minSaraly;
	}
	
	
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

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
