public class Player {

	private String name;
	private int balance;
	private boolean degree;
	private boolean married;
	private int baby;
	private Career job;

	private int position;
	
	/**
	 * 
	 */
	
	Player (){

	}
	
	/**
	 * When a Player class is created, it will require a String name parameter for the player name. It will then set the balance,
	 * the degree to false, married to false, and baby to 0. It will also set its initial position in the board.
    * @param name is the name of the player
    */
	Player (String name){
		
		this.name = name;
		balance = 200000;
		degree = false;
		married = false;
		baby = 0;
		
		position = 0;
	}
	
	/**
	 * Function returns name of the player
	 * @return name
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Function returns balance of the player
	 * @return balance
	 */
	public int getBalance () {
		return balance;
	}
	
	/**
	 * Function adds balance of player
	 * @param num - amount to be added
	 */
	
	public void addBalance (int num) {
		balance += num;
	}
	
	
	/**
	 * Function subtracts balance of player
	 * @param num - amount to be subtracted
	 */
	public void subtractBalance (int num) {
		balance -= num;
	}
	
	/**
	 * Function sets new career of player
	 * @param position is the title of the job
	 * @param salary is the salary of the job
	 * @param tax is the tax of the job
	 */
	public void setNewCareer (String position, int salary, int tax) {
		job = new Career(position, salary, tax);
	}
	
	/**
	 * Function sets new career of player
	 * @return married, TRUE - player is married FALSE - player is not married
	 */
	public boolean isMarried () {
		return married;
	}


	/**
	 * When called, will return the Career class of the player selected.
	 * @return the career fof player
	 */
	public Career getJob () {return job; }

	/**
	 * Function returns name and balance as a string
	 * @return text
	 */
	@Override
	public String toString() {
		return name + ":" + balance;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		return ((Player) obj).name.equalsIgnoreCase(name);
	}
}