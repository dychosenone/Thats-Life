
public class Career {
	
	private String position;
	private int salary;
	private int tax;
	
	private int min;
	private int max;

	/**
	 * Function returns name of the player
	 * @param position is the title of the job
	 * @param salary is the salary of the job
	 * @param tax is the tax of the job
	 */
	
	public Career (String position, int salary, int tax) {
		this.position = position;
		this.salary = salary;
		this.tax = tax;
	}
	
	/**
	 * Function returns salary of player
	 * @return salary
	 */
	public int getSalary () {
		return salary;
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
