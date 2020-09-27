package Model.Player;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;

public class Career {
	
	private final int TAX_MULTIPLE = 2000;
	
	private String position;
	private int salary;
	private int tax;

	private int max;
	private int ctr = 1;

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

	/**
	 * Returns the tax value of the player
	 * @return Tax of Player
	 */
	public int getTax () {
		return tax;
	}

	/**
	 * Returns the counter of Player. Counter to check if player has exceeded the number of raises.
	 * @return Counter of Raises
	 */
	public int getCtr () {
		return ctr;
	}

	/**
	 * Returns the max raises of the player.
	 * @return Max Raises of Player
	 */
	public int getMax () {
		return max;
	}
	
	/**
	 * Function returns position of player
	 * @return position
	 */
	public String getPosition () {
		return position;
	}

	/**
	 * Raises the salary of the player
	 * @param raise The value of Raise
	 * @return true of raise successful (within max raise range), false (above max raise range)
	 */
	public boolean raiseSalary (int raise) {
		System.out.println(ctr + ":" + max);
		if (ctr <= max) {
			salary += raise;
			tax += TAX_MULTIPLE;
			ctr++;
			return true;
		}
		
		else
			return false;
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
