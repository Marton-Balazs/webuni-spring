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
		private double percentVeryGood;
		private double percentGood;
		private double percentAvarage;
		private double percentNothing;
		private int limitVeryGood;
		private int limitGood;
		private int limitAvarage;
		
		public double getPercentVeryGood() {
			return percentVeryGood;
		}
		
		public void setPercentVeryGood(double percentVeryGood) {
			this.percentVeryGood = percentVeryGood;
		}
		
		public double getPercentGood() {
			return percentGood;
		}
		
		public void setPercentGood(double percentGood) {
			this.percentGood = percentGood;
		}
		
		public double getPercentAvarage() {
			return percentAvarage;
		}
		
		public void setPercentAvarage(double percentAvarage) {
			this.percentAvarage = percentAvarage;
		}
		
		public double getPercentNothing() {
			return percentNothing;
		}
		
		public void setPercentNothing(double percentNothing) {
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
