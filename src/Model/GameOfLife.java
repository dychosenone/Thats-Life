package Model;

import Model.ActionCard.ActionCard;
import Model.ActionCard.ActionCardDeck;
import Model.Career.CareerCard;
import Model.Career.CareerCardDeck;
import Model.Player.Career;
import Model.Player.Player;
import Model.Player.Players;
import Model.SalaryCard.SalaryCard;
import Model.SalaryCard.SalaryCardDeck;
import View.GUI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;

public class GameOfLife {
	
	private static final int MINPLAYERS = 2;
	private static final int MAXPLAYERS = 3;
	private static final Career NULL = null;
	
	private ActionCardDeck actionDeck;
	private CareerCardDeck careerDeck;
	private SalaryCardDeck salaryDeck;
	
	private Players players;
	private Player currentPlayer;
	private int wheel;
	private Player target = new Player();

	private boolean turn = false;	
	private boolean gameOver = false;
	
	private Board board;
	private Bank bank;

//INITIALIZING FUNCTIONS / GAME STARTING FUNCTIONS
	
	
	/**
	 * When a new Model.GameOfLife object is created, it generates a new Model.ActionCard.ActionCardDeck.
	 */
	
	GameOfLife (){
		players = new Players();
		actionDeck = new ActionCardDeck();
		careerDeck = new CareerCardDeck();
		salaryDeck = new SalaryCardDeck();

		actionDeck.showAllCards();
		
		board = new Board();
		bank = new Bank();
	}
	
	/**
	 * This function gets the number of players who will be playing then does a loop to add a new Player class to the
	 * Model.Player.Players collection.
	 * @param numofPlayers number of players playing That's Life
	 */
	
	public void enterPlayers (String name) {
		System.out.println("enterPlayers Function entered");
		
		players.AddPlayer(name);
			
	}
	
	/**
	 * This function enables players to specify the number of players through a System.in
	 * @return num - number of players
	 */
	public int getNumberOfPlayers(int numOfPlayers) {
		System.out.println("getNumberOfPlayers Function entered");
		
		return numOfPlayers;
	}

//PLAYER TURN FUNCTIONS
	
	/**
	 * Function sets all actions of players.
	 */

	public void processTurn (){
		turn = true;
		
		if(currentPlayer.getJob() == NULL) {
			jobSearch();
		}
		
		System.out.println(currentPlayer.getName() + " is Now Playing");
		System.out.println("Model.Career: " + currentPlayer.getJob().getPosition());
		
		System.out.println("You rolled a " + wheel);
		
		
	}
	
	public Space getSpace() {
		Space space = board.getSpace(currentPlayer.getPosition());
		
		return space;
	}
	
	public int interactSpace (int position) {
		
		Space space =board.getSpace(position);
			
		if (space instanceof OrangeSpace) {
			return 0;
		}
		
		else if (space instanceof MagentaSpace) {			
			
			switch (space.getName()){
				case "Job Search":
					return 1;
					
				case "Get Married":
					return 2;
				
				case "Choose Path":
					return 3;
			}
		}
		
		return -1;
	}

//MAGENTA SPACES FUNCTIONS
	public boolean isMagenta (Space space) {
		boolean check = false;
		
		if (space instanceof MagentaSpace) {
			check = true;
		}
		
		return check;
	}
	
	public void jobSearch() {
		CareerCard career = careerDeck.takeCard();
		SalaryCard salary = salaryDeck.takeCard();
		
		currentPlayer.setNewCareer(career, salary);
	}
	
//ORANGE SPACES FUNCTION
	
	/**
	 * Function takes an Action Card if current player lands on Orange Space.
	 */
	public ActionCard takeActionCard () {
		ActionCard card = actionDeck.takeCard();
		
		
		System.out.println(card.toString());
		System.out.println("CURRENT BALANCE " + currentPlayer.toString());
		
		return card;
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
	public boolean spinWheel () {
		
		wheel = 1 + (int)(Math.random() * 10);
		
		return true;
	}
	
	/**
	 * Function gets specific player entered by current player
	 * @return Player
	 */
	public int choosePlayer(int option) {
		
		Player target = new Player();
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
	
	public Player getCurrentPlayer () {
		return currentPlayer;
	}
	
	public ArrayList <Player> getPlayers(){
		return players.getPlayers();
	}
	
	public int getWheel () {
		return wheel;
	}
}
