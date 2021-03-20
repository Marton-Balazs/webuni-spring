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
		private int percent10;
		private int percent5;
		private int percent2;
		private int percent0;
		private int limit10;
		private int limit5;
		private double limit2;
		
		public int getPercent10() {
			return percent10;
		}
		public void setPercent10(int percent) {
			this.percent10 = percent;
		}
		public int getLimit10() {
			return limit10;
		}
		public void setLimit10(int limit) {
			this.limit10 = limit;
		}
		public int getPercent5() {
			return percent5;
		}
		public void setPercent5(int percent5) {
			this.percent5 = percent5;
		}
		public int getPercent2() {
			return percent2;
		}
		public void setPercent2(int percent2) {
			this.percent2 = percent2;
		}
		public int getPercent0() {
			return percent0;
		}
		public void setPercent0(int percent0) {
			this.percent0 = percent0;
		}
		public int getLimit5() {
			return limit5;
		}
		public void setLimit5(int limit5) {
			this.limit5 = limit5;
		}
		public double getLimit2() {
			return limit2;
		}
		public void setLimit2(double limit2) {
			this.limit2 = limit2;
		}
	}	
}
