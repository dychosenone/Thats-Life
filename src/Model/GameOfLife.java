package Model;

import Model.ActionCard.ActionCard;
import Model.BlueCard.*;
import Model.ActionCard.ActionCardDeck;
import Model.Career.CareerCard;
import Model.Career.CareerCardDeck;
import Model.HouseCard.HouseCard;
import Model.HouseCard.HouseCardDeck;
import Model.Player.Player;
import Model.Player.Players;

import Model.SalaryCard.SalaryCard;
import Model.SalaryCard.SalaryCardDeck;

import java.util.ArrayList;


public class GameOfLife {
	
	private final int FIRST_PLACE = 1000000;
	private final int SECOND_PLACE = 500000;
	private final int THIRD_PLACE = 200000;
	private int place = 1;
	
	private final int RETIRE_BABY_MULTIPLE = 100000;
	
	private ActionCardDeck actionDeck;
	private CareerCardDeck careerDeck;
	private SalaryCardDeck salaryDeck;
	private BlueCardDeck blueCardDeck;
	private HouseCardDeck houseCardDeck;
	
	private Players players;
	private Player currentPlayer;
	public int wheel;
	
	private Board board;
	private Bank bank;
	
	private int retiredPlayers;

//INITIALIZING FUNCTIONS / GAME STARTING FUNCTIONS
	
	
	/**
	 * When the gameOfLife is created, it generates:
	 * - ActionCard, CareerCard, salaryCard, blueCard, houseCard decks
	 * - the game backend board
	 * - the bank
	 * - and an arrayList of Players
	 */
	
	public GameOfLife (){
		players = new Players();
		actionDeck = new ActionCardDeck();
		careerDeck = new CareerCardDeck();
		salaryDeck = new SalaryCardDeck();
		blueCardDeck = new BlueCardDeck();
		houseCardDeck = new HouseCardDeck ();
		
		board = new Board();
		bank = new Bank();
		
		retiredPlayers = 0;
	}

	/**
	 * Add player name to the Players class.
	 * @param name Name of Player
	 */
	
	public void enterPlayers (String name) {
		System.out.println("enterPlayers");
		players.AddPlayer(name);
			
	}
	
	/**
	 * This function enables players to specify the number of players through a System.in
	 * @param numOfPlayers number of Players
	 * @return num - number of players
	 */
	public int getNumberOfPlayers(int numOfPlayers) {
		System.out.println("getNumberOfPlayers");
		return numOfPlayers;
	}

//PLAYER TURN FUNCTIONS

	/**
	 * This function gets the type of space the currentPlayer is currently in.
	 * @return Space
	 */
	public Space getSpace() {
		Space space = board.getSpace(currentPlayer.getPosition());
		
		return space;
	}

	/**
	 * This function interacts with the given space position. It checks the tile type and returns an integer value indicating the space type.
	 * @param position Position of player
	 * @return spaceType
	 */

	public int interactSpace (int position) {
		
		Space space =board.getSpace(position);
		System.out.println(space.getName());
		//ORANGE SPACE	
		if (space.getName().equalsIgnoreCase("Orange")) {
			return 0;
		}
		//MAGENTA SPACE
		else if (space instanceof MagentaSpace) {			
			
			switch (((MagentaSpace) space).getMagentaType()){
				case "jobSearch":
					return 1;		
				case "getMarried":
					return 2;
				case "choosePath":
					return 3;
				case "haveChild" :
					return 4;
				case "buyHouse":
					return 5;
				case "graduation":
					return 6;
				case "collegeCareerChoice":
					return 7;
			}
		}
		//BLUE SPACE
		else if(space.getName().equalsIgnoreCase("Blue")) {
			return 8;
		}
		
		else if (space.getName().equalsIgnoreCase("Green")) {
			return 9;
		}
		return -1;
	}
	
//ORANGE SPACES FUNCTION
	
	/**
	 * Function takes an Action Card if current player lands on Orange Space.
	 * @return Action Card
	 */
	public ActionCard takeActionCard () {
		ActionCard card = actionDeck.takeCard();
			
		return card;
	}
	
//GREEN SPACES FUNCTION

	/**
	 * Raises the Salary of the Current Player
	 * @return the playerRaise
	 */
	public int payRaise () {
		int raise = 1 + (int)(Math.random() * 10);
		raise *= 10000;
		
		if (currentPlayer.getJob().raiseSalary(raise)) {
			return raise;
		}
		else {
			return -1;
		}
	}
	/**
	 * Adds the salary of the current player minus tax
	 */
	public void payDay () {
		currentPlayer.addBalance(currentPlayer.getJob().getSalary() - currentPlayer.getJob().getTax());
	}

	
//BLUE SPACES FUNCTIONS

	/**
	 * Thus function takes a blueCard from the blueCardDeck
	 * @return Blue Card
	 */
	public BlueCard takeBlueCard () {
		
		BlueCard card = blueCardDeck.takeCard();
		return card;
		
	}

	/**
	 * This function determines the effect of the blue card on the player.
	 * @param card BlueCard
	 * @return amount to be subtracted or added on the player/s
	 */
	public int blueCardEffect (BlueCard card) {
		int amount = card.cardAction(currentPlayer, currentPlayer.getJob(), players.getSize());
		System.out.println(card.getCardName());
		
		if(card.checkPlayerCareer(currentPlayer.getJob())) {
			System.out.println("Checked");
			addBalance (currentPlayer, amount);
		}
		else {
			for(int i = 0; i < players.getSize(); i++){
				Player p = players.getPlayers().get(i);
				if(p.getJob() != null) {
					if(card.checkPlayerCareer(p.getJob())) {
						System.out.println("Added");
						addBalance(p, amount);
					}
				}
			}
			subtractBalance (currentPlayer, amount);
			System.out.println("Minus");
		}
		return amount;
	}
	
	
//MAGENTA SPACES FUNCTIONS

	/**
	 * Checks if the position of the current player is in is a magenta space.
	 * @return isMagenta (true if tile is magenta, false otherwise)
	 */
	public boolean isMagenta () {
		Space space = board.getSpace(currentPlayer.getPosition());
		boolean check = false;
		
		if (space instanceof MagentaSpace) {
			check = true;
		}

		return check;
	}

	/**
	 * When a player lands on a jobSearch tile, it sets the career and salary of the current player
	 * @param c Career Card
	 * @param s Salary Card
	 */
	public void jobSearch(CareerCard c, SalaryCard s) {
		currentPlayer.setNewCareer(c, s);
	}

	/**
	 * When the player gets married, if they spin an even number, they collect 10000, if odd, collect 5000.
	 * @param tempWheel Wheel Value
	 */
	public void getMarried (int tempWheel) {
		if (tempWheel % 2 == 0) {
			collectFromEveryone(10000);
			currentPlayer.getMarried();
		}
		else {
			collectFromEveryone(5000);
			currentPlayer.getMarried();
		}
	}

	/**
	 * THis function takes in the choice of the player, checks if the balance is sufficient to purchase the house.
	 * @param option House Option
	 * @return Successful Purchase (true is successful, false otherwise (insufficient funds)
	 */
	public boolean buyHouse (int option) {
		HouseCard house = houseCardDeck.getCard(option);
		
		if (currentPlayer.getBalance() >= house.getValue()) {
			currentPlayer.buyHouse(house);
			return true;
		}
		
		else {
			houseCardDeck.returnCard(option);
			return false;
		}
		
		
	}

	/**
	 * This function returns the entire deck of HouseCards.
	 * @return HouseCard deck
	 */
	public ArrayList <HouseCard> getHouseCards () {
		return houseCardDeck.getHouseCards();
	}

	/**
	 * This function takes a career card, makes sure the card is not null and returns it to the player.
	 * @return Career Card
	 */
	public CareerCard takeCareerCard () {
		CareerCard c = careerDeck.takeCard();
		
		if(c != null)
			return c;
		else
			return null;
	}

	/**
	 * This function is called to return a specific career card to the deck. Calls the returnCard function which
	 * sets availability to true.
	 * @param c Career Card
	 */
	public void returnCareerCard (CareerCard c) {
		careerDeck.returnCard(c);
	}

	/**
	 * Returns career card by finding the card of the current player's career card.
	 * Calls the return card function of Career Card Deck
	 */
	public void returnCareerCard() {
		CareerCard c = careerDeck.findCard(currentPlayer.getJob());
		careerDeck.returnCard(c);
	}

	/**
	 * Calls the takeCard function of Salary Card Deck. This then returns the SalaryCard taken
	 * @return Salary Card
	 */
	public SalaryCard takeSalaryCard () {
		return salaryDeck.takeCard();
	}

	/**
	 * This function returns the salary card to the deck.
	 * @param s Salary Card to be returned
	 */
	public void returnSalaryCard (SalaryCard s) {
		salaryDeck.returnCard(s);
	}

	/**
	 * Returns the salary card.
	 */
	public void returnSalaryCard () {
		SalaryCard s = salaryDeck.findCard(currentPlayer.getJob());
		salaryDeck.returnCard(s);
	}

	/**
	 * This function sets the current Player a new career given a Salary Card and Career Card
	 * @param c Career Card
	 * @param s Salary Card
	 */
	public void setCareer (CareerCard c, SalaryCard s) {
		currentPlayer.setNewCareer(c, s);
	}

	/**
	 * This function sets the current players graduate boolean to true.
	 */
	public void graduate () {
		currentPlayer.graduate();
	}

	/**
	 * This checks which path the current player is taking and which tile the designated path starts.
	 * @param path Path Number
	 * @return Tile number to jump to Path
	 */
	public int choosePath (int path) {
		if (currentPlayer.getPosition() == 0) {		
			switch (path) {
			case 1: //START CAREER
				return 12;
			case 2://START COLLEGE
				return 1;
			}
		}
		else if (currentPlayer.getPosition() == 46) {
			switch (path) {
			case 1://FAMILY PATH
				return 47;
			case 2://CAREER PATH
				return 55;
			}
		}
		System.out.println(currentPlayer.position);
		return 0;

	}

	// Checks if Space has a jump

	/**
	 * This function checks if the space has a jump value.
	 * @return true if value is not -1, false if -1 (-1 denotes no jump)
	 */
	public boolean isJump (){
		Space s = getSpace();
		if(s.pathJump() != -1)
			return true;
		else return false;
	}

	// Gets the jump

	/**
	 * This function gets the jump of the selected space.
	 * @return Path Jump Position
	 */
	public int getJump (){

		Space s = getSpace();
		return s.pathJump();

	}
	

//PLAYER FUNCTIONS
	
	/**
	* Function finishes turn of current player and gets next player
	*/
	public void nextTurn () {
		currentPlayer = players.getNextPlayer(currentPlayer);
	}
	
	/**
	 * Function spins wheel
	 * 
	 * @return Wheel - number of spaces player will move
	 */
	public static int spinWheel () {
		
		return 1 + (int)(Math.random() * 10);
	}
	
	/**
	 * Function gets specific player entered by current player
	 * @param option Player Option
	 * @return Player
	 */
	public int choosePlayer(int option) {
		
		ArrayList <Player> temp = players.getPlayers();
					
		switch (option) {
		case 1:
				
			if(!temp.get(option-1).equals(currentPlayer)) {
				return 0;
			}
			else
				System.out.println("INVALID PLAYER");
			break;
		case 2:
			if(!temp.get(option-1).equals(currentPlayer)) {
				return 1;
			}
			else
				System.out.println("INVALID PLAYER");
			break;
		case 3:
			if(!temp.get(option-1).equals(currentPlayer)) {
				return 2;
			}
			else
				System.out.println("INVALID PLAYER");
			break;
		}

		return -1;
		
	}

	/**
	 * The function takes a player and adds value to the selected player's balance
	 * @param player player to have balance added
	 * @param value the amount to be added
	 */
	public void addBalance (Player player, int value) {
		player.addBalance(value);
	}

	/**
	 * The function takes a player and subtracts value to the selected player's balance
	 * @param player player to have balance subtracted
	 * @param value the amount to be subtracted
	 */
	public void subtractBalance (Player player, int value) {
		player.subtractBalance(value);
	}

	/**
	 * This function loops through the ArrayList of players and take the amount and subtracts balance to all players except currentPlayer.
	 * After adding balance, it adds the balance of the CurrentPlayer.
	 * @param amt Amount to be taken
	 */
	public void collectFromEveryone (int amt) {
		ArrayList <Player> temp = players.getPlayers();
		currentPlayer.addBalance(amt * (players.getSize() - 1));
		int i;
		
		for (i = 0; i < temp.size(); i++){     
			if (!temp.get(i).equals(currentPlayer) && !temp.get(i).isFinish()) {
				temp.get(i).subtractBalance(amt);
				currentPlayer.addBalance(amt);
			}
    	}
 
	}

	/**
	 * This function loops through the ArrayList of players and take the amount and adds balance to all players except currentPlayer.
	 * After adding balance, it subtracts the balance of the CurrentPlayer.
	 * @param amt Amount to be paid
	 */
	public void payEveryone (int amt) {
		ArrayList <Player> temp = players.getPlayers();
		int i;
		
		for (i = 0; i < temp.size(); i++){     
			if (!temp.get(i).equals(currentPlayer) && !temp.get(i).isFinish()) {
				temp.get(i).addBalance(amt);
				currentPlayer.subtractBalance(amt);
			}
    	}
 
	}

	/**
	 * This function calls the Bank class' takeLoan function and applies it to the currentPlayer.
	 */
	public void getLoan (){
		bank.takeLoan (currentPlayer);
	}

	/**
	 * This function checks if the player has an existing loan, if true, pays back the loan, if false returns false
	 * @return true if loan pay successful (has loan), false otherwise
	 */
	public boolean payLoan() {
		if (currentPlayer.hasDebt()) {		
			bank.payLoan(currentPlayer);
			return true;
		}
		
		return false;
	}

	/**
	 * Checks if the current position of the player is at the end,
	 * if true, determines the place of the player and calls the retire function.
	 * @return returns if player is at end (true), otherwise return false
	 */
	public boolean isEnd () {
		if(board.isEnd(currentPlayer.getPosition())) {
			switch (place) {
			case 1:
				currentPlayer.retire (FIRST_PLACE, RETIRE_BABY_MULTIPLE, 1);
				break;
			case 2:
				currentPlayer.retire (SECOND_PLACE, RETIRE_BABY_MULTIPLE, 2);
				break;
			case 3:
				currentPlayer.retire (THIRD_PLACE, RETIRE_BABY_MULTIPLE, 3);
				break;
			}
			place ++;
			return true;
		}
		else
			return false;
	}
	/**
	 * Returns the current place.
	 * @return the current place (standing)
	 */
	public int getPlace () {
		return place;
	}

	/**
	 * Returns the current player taking a turn.
	 * @return Current Player
	 */
	public Player getCurrentPlayer () {
		return currentPlayer;
	}

	/**
	 * Returns all the players currently playing.
	 * @return ArrayList of Players
	 */
	public ArrayList <Player> getPlayers(){
		return players.getPlayers();
	}

	/**
	 * Calls the PrintSpaces function of the board class.
	 */
	public void printSpaces () {
		board.printSpaces();
	}

	/**
	 * Gets the Spin Wheel Value
	 * @return Spin Wheel Value
	 */
	public int getWheel () {
		return wheel;
	}

	/**
	 * Sets the first player to make a move.
	 */
	public void getStarter() {
		currentPlayer = players.getPlayers().get(0);
	}

	/**
	 * Checks if all players have finished the game
	 * @return true if the game is finished, false if not
	 */
	public boolean gameOver () {
		int ctr = 0;
		int i;
		
		for (i = 0; i < getPlayers().size(); i++) {
			
			if(getPlayers().get(i).isFinish())
				ctr ++;
		}

		retiredPlayers = ctr;
		return ctr == getPlayers().size();
	}

	/**
	 * This function decides the winner given the total amount of money earned during the game.
	 * @return Player winner
	 */
	public Player decideWinner () {

		ArrayList <Player> temp = getPlayers();
		int i;
		Player winner = null;
		
		for(i = 0; i < temp.size(); i++) {
			
			if(winner == null) {
				winner = temp.get(i);
			}
			
			if (winner.getBalance() <= temp.get(i).getBalance()){
				if (winner.getBalance() == temp.get(i).getBalance()) {
					if(winner.getPlace() < temp.get(i).getPlace()) {
						winner = temp.get(i);
					}
				}
					
				else
					winner = temp.get(i);
			}
		}
			
		return winner;
		
	}

	/**
	 * Gets the number of retired players in the game.
	 * @return Number of Retired Players
	 */
	public int getNumOfRetiredPlayers () {
		return this.retiredPlayers;
	}

	/**
	 * Gets the number of players playing.
	 * @return Size of the Players ArrayList (Number of Players)
	 */
	public int getPlayersSize () {
		return players.getSize();
	}
}
