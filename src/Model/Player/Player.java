package Model.Player;

import Model.Career.CareerCard;
import Model.HouseCard.HouseCard;
import Model.SalaryCard.SalaryCard;

public class Player {
	
	private static final int LOAN_PAYMENT_MULTIPLE = 25000;
	private static final int MAX_BABIES = 4;
	
	private String name;
	private int balance;
	private int debt;
	private boolean degree;
	private boolean married;
	private int baby;
	private Career job;
	private House house;

	public int position;
	
	public boolean finish = false;
	
	/**
	 * 
	 */
	
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
	
	public void move () {
		position ++;
	}

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
	
	public int getPosition () {
		return position;
	}
	
	public int getBabies () {
		return baby;
	}
	
	public boolean getDegree() {
		return degree;
	}
	
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
	
	public void getLoan (int num) {
		debt += num;
	}
	
	public void payLoan (int num) {
		debt -= num;
	}
	
	/**
	 * Function sets new career of player
	 * @param position is the title of the job
	 * @param salary is the salary of the job
	 * @param tax is the tax of the job
	 */
	public void setNewCareer (CareerCard c, SalaryCard s) {
		job = new Career(c, s);
	}
	
	public boolean hasCareer () {
		return job != null;
	}
	
	public void buyHouse (HouseCard h) {
		house = new House (h);
		balance -= h.getValue();
	}
	
	public void sellHouse () {
		balance += house.getValue();
		house = null;
	}
	
	public House getHouse () {
		return house;
	}
	
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
	 * Function returns name and balance as a string
	 * @return text
	 */
	
	public boolean hasDebt () {
		return debt != 0;
	}
	
	public int getDebt () {
		return debt;
	}
	
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