package Model.Player;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;

public class Career {
	
	private String position;
	private int salary;
	private int tax;

	private int max;

	/**
	 * Function returns name of the player
	 * @param position is the title of the job
	 * @param salary is the salary of the job
	 * @param tax is the tax of the job
	 */
	
	public Career (CareerCard c, SalaryCard s) {
		position = c.getCareerName();
		salary = s.getSalary();
		tax = s.getTaxDue();
		
		max = c.getPayRaise();
	}
	
	/**
	 * Function returns salary of player
	 * @return salary
	 */
	public int getSalary () {
		return salary;
	}
	
	public int getTax () {
		return tax;
	}
	
	/**
	 * Function returns position of player
	 * @return position
	 */
	public String getPosition () {
		return position;
	}
	
	/**
	 * Function sets new salary of job
	 * @param newSalary - new salary of job
	 */
	public void setSalary (int newSalary) {
		this.salary = newSalary;
	}
	
	/**
	 * Function sets new position, salary and tax
	 * @param position is the title of the job
	 * @param salary is the salary of the job
	 * @param tax is the tax of the job
	 */
	public void setNewJob (String position, int salary, int tax) {
		this.position = position;
		this.salary = salary;
		this.tax = tax;
	}
}
