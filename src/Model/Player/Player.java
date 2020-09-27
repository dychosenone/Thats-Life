package Model.Player;

import Model.Career.CareerCard;
import Model.HouseCard.HouseCard;
import Model.SalaryCard.SalaryCard;

public class Player {
	
	private static final int LOAN_PAYMENT_MULTIPLE = 25000;
	private static final int MAX_BABIES = 4;
	
	private String name;
	private boolean degree;
	private boolean married;
	
	private int balance;
	private int debt;
	
	private int baby;
	private Career job;
	private House house;

	public int position;
	
	public boolean finish = false;
	
	public Player (){

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
	 * When this function is called, it moves the player one space forward by setting the position to add 1.
	 */
	public void move () {
		position ++;
	}

	/**
	 * When this function is called, it moves to the position set by the parameter.
	 * @param position the position to be moved to.
	 */
	public void jumpTo (int position){

		this.position = position;

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
	 * returns the current position of the player
	 * @return current position
	 */
	public int getPosition () {
		return position;
	}

	/**
	 * returns the number of babies the player has.
	 * @return number of babies
	 */
	public int getBabies () {
		return baby;
	}

	/**
	 * returns if the player has a degree or not.
	 * @return return degree boolean value of player (has degree = true, no degree = false)
	 */
	public boolean getDegree() {
		return degree;
	}

	/**
	 * sets the position of the player to parameter P
	 * @param p position number value
	 */
	public void setPosition (int p) {
		position = p;
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
	 * When player gets loan, it is added to the debt variable.
	 * @param num amount of loan to be added
	 */
	public void getLoan (int num) {
		debt += num;
	}

	/**
	 * When player gets loan, it is subtracted to the debt variable.
	 * @param num amount of loan to be subtracted
	 */
	public void payLoan (int num) {
		debt -= num;
	}

	/**
	 * When a player has a new career, the SalaryCard and CareerCard is needed. T
	 * @param c CareerCard to set new Career
	 * @param s SalaryCard to  set new Career
	 */
	public void setNewCareer (CareerCard c, SalaryCard s) {
		job = new Career(c, s);
	}

	/**
	 * Returns a boolean value that checks if the player has a job or not by checking if the Career class is null or not.
	 * @return true if has job, false if no job
	 */
	public boolean hasCareer () {
		return job != null;
	}

	/**
	 * When a player purchases a house, the HouseCard is needed. The house is then set by th new houseCard. After a new house is purchased,
	 * the total amount of money of the player is subtracted by the total value of the new house purchased.
	 * @param h HouseCard of the new player's house
	 */
	public void buyHouse (HouseCard h) {
		house = new House (h);
		balance -= h.getValue();
	}

	/**
	 * When a house is sold, the value of the house is added to the total amount of the player's money. The HouseCard of the player is then
	 * set to null.
	 */
	public void sellHouse () {
		balance += house.getValue();
		house = null;
	}

	/**
	 * Gets the HouseCard of the player, and returns it.
	 * @return Player HouseCard
	 */
	public House getHouse () {
		return house;
	}

	/**
	 * Checks if the player has a house by checking if the HouseCard is null or not.
	 * @return true if player has a house, false if player has no house.
	 */
	public boolean hasHouse () {
		return house != null;
	}
	
	/**
	 * Function sets new career of player
	 * @return married, TRUE - player is married FALSE - player is not married
	 */
	public boolean isMarried () {
		return married;
	}

	/**
	 * When the player gets married, the married boolean is set to true.
	 */
	public void getMarried () {
		married = true;
	}

	public boolean haveBabies (int num) {
		System.out.println(isMarried());
		System.out.println(baby < MAX_BABIES);
		if (isMarried() && baby < MAX_BABIES) {
			baby += num;
			return true;
		}
		else
			return false;
	}

	/**
	 * When called, will return the Model.Career class of the player selected.
	 * @return the career fof player
	 */
	public Career getJob () {
		return job; 
	}
	
	public void graduate () {
		degree = true;
	}
	
	public boolean hasDegree () {
		return degree;
	}

	/**
	 * Checks if the player has debt.
	 * @return true if debt is not a zero value, false if dept is 0.
	 */
	public boolean hasDebt () {
		return debt != 0;
	}

	/**
	 * returns the total debt of the player.
	 * @return debt of player
	 */
	public int getDebt () {
		return debt;
	}

	/**
	 * When the player retires, the player will set its finish to true to let the game know he is done.
	 * The price and baby multiplied by the multiplier is then added to the player.
	 * Adds the house to its total balance by selling it.
	 * Pays all loan remaining.
	 * @param prize
	 * @param babyMultiple
	 */
	public void retire (int prize, int babyMultiple) {
		finish = true;
		addBalance (prize);
		addBalance (baby * babyMultiple);
		
		if(house != null) {
			sellHouse();
		}
		
		while(hasDebt()) {
			payLoan(LOAN_PAYMENT_MULTIPLE);
		}
	}

	/**
	 * returns the finish status of the player.
	 * @return if finished, true, if not finished, false
	 */
	public boolean isFinish () {
		return finish;
	}
	
	@Override
	public String toString() {
		return name + ":" + balance + ": POSITION: " + position;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		return ((Player) obj).name.equalsIgnoreCase(name);
	}
}