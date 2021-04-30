package hu.webuni.hr.martonBalazs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//public enum CompanyType {
//	
//	BT, KFT, ZRT, NYRT;
//
//}

@Entity
public class CompanyType {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
