package hu.webuni.hr.martonBalazs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {
	
	private Employee employee = new Employee();
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	public static class Employee{
		
		private Special special = new Special();

		public Special getSpecial() {
			return special;
		}

		public void setSpecial(Special special) {
			this.special = special;
		}
	}
	
	public static class Special{
		private int percentVeryGood;
		private int percentGood;
		private int percentAvarage;
		private int percentNothing;
		private int limitVeryGood;
		private int limitGood;
		private int limitAvarage;
		
		public int getPercentVeryGood() {
			return percentVeryGood;
		}
		public void setPercentVeryGood(int percentVeryGood) {
			this.percentVeryGood = percentVeryGood;
		}
		public int getPercentGood() {
			return percentGood;
		}
		public void setPercentGood(int percentGood) {
			this.percentGood = percentGood;
		}
		public int getPercentAvarage() {
			return percentAvarage;
		}
		public void setPercentAvarage(int percentAvarage) {
			this.percentAvarage = percentAvarage;
		}
		public int getPercentNothing() {
			return percentNothing;
		}
		public void setPercentNothing(int percentNothing) {
			this.percentNothing = percentNothing;
		}
		public int getLimitVeryGood() {
			return limitVeryGood;
		}
		public void setLimitVeryGood(int limitVeryGood) {
			this.limitVeryGood = limitVeryGood;
		}
		public int getLimitGood() {
			return limitGood;
		}
		public void setLimitGood(int limitGood) {
			this.limitGood = limitGood;
		}
		public int getLimitAvarage() {
			return limitAvarage;
		}
		public void setLimitAvarage(int limitAvarage) {
			this.limitAvarage = limitAvarage;
		}
	}	
}
