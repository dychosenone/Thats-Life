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
	 * When a new Model.GameOfLife object is created, it generates a new Model.ActionCard.ActionCardDeck.
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
	 * This function gets the number of players who will be playing then does a loop to add a new Player class to the
	 * Model.Player.Players collection.
	 * @param numofPlayers number of players playing That's Life
	 */
	
	public void enterPlayers (String name) {
		System.out.println("enterPlayers");
		players.AddPlayer(name);
			
	}
	
	/**
	 * This function enables players to specify the number of players through a System.in
	 * @return num - number of players
	 */
	public int getNumberOfPlayers(int numOfPlayers) {
		System.out.println("getNumberOfPlayers");
		return numOfPlayers;
	}

//PLAYER TURN FUNCTIONS
	
	public Space getSpace() {
		Space space = board.getSpace(currentPlayer.getPosition());
		
		return space;
	}

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
	 */
	public ActionCard takeActionCard () {
		ActionCard card = actionDeck.takeCard();
			
		return card;
	}
	
//GREEN SPACES FUNCTION
	
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
	
	public void payDay () {
		currentPlayer.addBalance(currentPlayer.getJob().getSalary() - currentPlayer.getJob().getTax());
	}

	
//BLUE SPACES FUNCTIONS
	public BlueCard takeBlueCard () {
		
		BlueCard card = blueCardDeck.takeCard();
		return card;
		
	}
	
	public int blueCardEffect (BlueCard card) {
		int amount = card.cardAction(currentPlayer, currentPlayer.getJob(), players.getSize());
		
		if(card.checkPlayerCareer(currentPlayer.getJob()) == true) {
			addBalance (currentPlayer, amount);
			return amount;
		} 
		else {

			for(int i = 0; i < players.getSize(); i++){
				Player p = players.getPlayers().get(i);
				if(p.equals(currentPlayer) == true){
					if(card.checkPlayerCareer(p.getJob()) == true)
						addBalance(p, amount);
				}
			}
			subtractBalance (currentPlayer, amount);
			return amount;
		}
	}
	
	
//MAGENTA SPACES FUNCTIONS
	public boolean isMagenta () {
		Space space = board.getSpace(currentPlayer.getPosition());
		boolean check = false;
		
		if (space instanceof MagentaSpace) {
			check = true;
		}

		return check;
	}

	public void jobSearch(CareerCard c, SalaryCard s) {
		currentPlayer.setNewCareer(c, s);
	}
	

	public void getMarried (int tempWheel) {
		if (tempWheel % 2 == 0) {
			collectFromEveryone(10000);
			
		}
		else {
			collectFromEveryone(5000);
		}
	}
	
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
	
	public ArrayList <HouseCard> getHouseCards () {
		return houseCardDeck.getHouseCards();
	}

	public CareerCard takeCareerCard () {
		CareerCard c = careerDeck.takeCard();
		
		if(c != null)
			return c;
		else
			return null;
	}
	
	public void returnCareerCard (CareerCard c) {
		careerDeck.returnCard(c);
	}
	
	public void returnCareerCard() {
		CareerCard c = careerDeck.findCard(currentPlayer.getJob());
		careerDeck.returnCard(c);
	}
	
	public SalaryCard takeSalaryCard () {
		return salaryDeck.takeCard();
	}
	
	public void returnSalaryCard (SalaryCard s) {
		salaryDeck.returnCard(s);
	}
	
	
	public void setCareer (CareerCard c, SalaryCard s) {
		currentPlayer.setNewCareer(c, s);
	}
	
	public void graduate () {
		currentPlayer.graduate();
	}
	
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
	public boolean isJump (){
		Space s = getSpace();
		if(s.pathJump() != -1)
			return true;
		else return false;
	}

	// Gets the jump
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
	
	public void addBalance (Player player, int value) {
		player.addBalance(value);
	}
	
	public void subtractBalance (Player player, int value) {
		player.subtractBalance(value);
	}
	
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
	
	public void getLoan (){
		bank.takeLoan (currentPlayer);
	}
	
	public boolean payLoan() {
		if (currentPlayer.hasDebt()) {		
			bank.payLoan(currentPlayer);
			return true;
		}
		
		return false;
	}
	
	public boolean isEnd () {
		if(board.isEnd(currentPlayer.getPosition())) {
			switch (place) {
			case 1:
				currentPlayer.retire (FIRST_PLACE, RETIRE_BABY_MULTIPLE);
				break;
			case 2:
				currentPlayer.retire (SECOND_PLACE, RETIRE_BABY_MULTIPLE);
				break;
			case 3:
				currentPlayer.retire (THIRD_PLACE, RETIRE_BABY_MULTIPLE);
				break;
			}
			place ++;
			return true;
		}
		else
			return false;
	}
	
	public int getPlace () {
		return place;
	}
	
	
	public Player getCurrentPlayer () {
		return currentPlayer;
	}
	
	public ArrayList <Player> getPlayers(){
		return players.getPlayers();
	}
	
	public void printSpaces () {
		board.printSpaces();
	}
	
	public int getWheel () {
		return wheel;
	}
	
	public void getStarter() {
		currentPlayer = players.getPlayers().get(0);
	}
	
	public boolean gameOver () {
		int ctr = 0;
		int i;
		
		for (i = 0; i < getPlayers().size(); i++) {
			
			if(getPlayers().get(i).isFinish())
				ctr ++;
		}
		System.out.println(retiredPlayers);
		retiredPlayers = ctr;
		return ctr == getPlayers().size();
	}
	
	public Player decideWinner () {

		ArrayList <Player> temp = getPlayers();
		int i;
		Player winner = null;
		
		for(i = 0; i < temp.size(); i++) {
			
			if(winner == null) {
				winner = temp.get(i);
			}
			
			if (winner.getBalance() < temp.get(i).getBalance())
				winner = temp.get(i);
		}
			
		return winner;
		
	}
	
	public int getNumOfRetiredPlayers () {
		return retiredPlayers;
	}
	
	public int getPlayersSize () {
		return players.getSize();
	}
}
