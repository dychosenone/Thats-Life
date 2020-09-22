package Model;

import Model.ActionCard.ActionCard;
import Model.BlueCard.*;
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
	private BlueCardDeck blueCardDeck;
	
	private Players players;
	private Player currentPlayer;
	public int wheel;
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
		blueCardDeck = new BlueCardDeck();
		
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
			}
		}
		//BLUE SPACE
		else if(space.getName().equalsIgnoreCase("Blue")) {
			return 5;
		}
		
		else if (space.getName().equalsIgnoreCase("Green")) {
			return 6;
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
	
	public void jobSearch() {
		CareerCard career = careerDeck.takeCard();
		SalaryCard salary = salaryDeck.takeCard();
		
		currentPlayer.setNewCareer(career, salary);
	}
	
	public void getMarried (int tempWheel) {
		int i;
		ArrayList <Player> temp = getPlayers();
		if (tempWheel % 2 == 0) {
			for (i = 0; i < temp.size(); i++) {
				currentPlayer.addBalance(10000);
				temp.get(i).subtractBalance(10000);
				currentPlayer.getMarried();
			}
			
		}
		else {
			for (i = 0; i < temp.size(); i++) {
				currentPlayer.addBalance(5000);
				temp.get(i).subtractBalance(5000);
			}
		}
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
	
	public void printSpaces () {
		board.printSpaces();
	}
	
	public int getWheel () {
		return wheel;
	}
}
