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
	
	private GUI ui;
	private Controller cont;

//INITIALIZING FUNCTIONS / GAME STARTING FUNCTIONS
	/**
	 * When a new Model.GameOfLife object is created, it generates a new Model.ActionCard.ActionCardDeck.
	 */
	
	GameOfLife (){
		players = new Players();
		actionDeck = new ActionCardDeck();
		careerDeck = new CareerCardDeck();
		salaryDeck = new SalaryCardDeck();
		
		board = new Board();
		
		ui = new GUI();
		cont = new Controller (ui, this);
		
		

	}
	
	/**
	 * This function gets the number of players who will be playing then does a loop to add a new Player class to the
	 * Model.Player.Players collection.
	 * @param numofPlayers number of players playing That's Life
	 */
	
	public void enterPlayers (int numofPlayers) {
		int i = 0;
		
		do {
			players.AddPlayer(i);
			i++;
			
		}while (i < numofPlayers);
	}
	
	/**
	 * This function enables players to specify the number of players through a System.in
	 * @return num - number of players
	 */
	public int getNumberOfPlayers() {
		Scanner scan = new Scanner (System.in);
		int num;
		boolean run = true;
		
		do {
			ui.displayText("Enter Number of Players: ");
			
			
			num = Integer.parseInt (scan.nextLine());
			
			if (num >= MINPLAYERS && num <= MAXPLAYERS) {
				run = false;
			}
			
		}while (run);
		
		return num;
	}

//PLAYER TURN FUNCTIONS
	
	/**
	 * Function sets all actions of players.
	 */

	public void processTurn (){
		turn = true;
		int option;
		boolean done = false;
		Scanner scan = new Scanner(System.in);
		
		if(currentPlayer.getJob() == NULL) {
			jobSearch();
		}
		
		System.out.println(currentPlayer.getName() + " is Now Playing");
		System.out.println("Model.Career: " + currentPlayer.getJob().getPosition());
		
		wheel = spinWheel ();
		System.out.println("You rolled a " + wheel);
		
		for (int i = 1; i <= wheel; i++) {
			
			currentPlayer.move();
			
			Space space = board.getSpace(currentPlayer.getPosition());
			
			System.out.println(currentPlayer.getPosition());
			
			if (isMagenta(space) && i != wheel )
				interactSpace(currentPlayer.getPosition());
		}
		
		interactSpace(currentPlayer.getPosition());
		
		Scanner in = new Scanner (System.in);
		System.out.print("Input to continue...");
		String dump = in.next();
	}
	
	public void interactSpace (int position) {
		
		Space space =board.getSpace(position);
			
		if (space instanceof OrangeSpace) {
			System.out.println("You landed On " + space.getName());
			takeActionCard();
		}
		
		else if (space instanceof MagentaSpace) {			
			
			switch (space.getName()){
				case "Job Search":
					System.out.println("You landed On " + space.getName());
					jobSearch();
					break;
					
				case "Get Married":
					System.out.println("You landed On " + space.getName());
					break;
			}
		}
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
	public void takeActionCard () {
		int i;
		ActionCard card = actionDeck.takeCard();
		
		ArrayList <Player> temp = players.getPlayers();
		
		Player target;
		
		System.out.println(card.toString());
		System.out.println("CURRENT BALANCE " + currentPlayer.toString());
		
		
		switch (card.getCardType()) {
		case 1:
			// collects money from bank
			currentPlayer.addBalance(card.getValue());
			System.out.println("NEW BALANCE " + currentPlayer.toString());
			break;
		case 2:
			// give money to bank
			currentPlayer.subtractBalance(card.getValue());
			System.out.println("NEW BALANCE " + currentPlayer.toString());
			break;
		case 3:
			
			// give money to a player
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				target = choosePlayer();
				currentPlayer.subtractBalance(card.getValue());
				players.AddBalance(card.getValue(), target);
				System.out.println("NEW BALANCE " + currentPlayer.toString());
				System.out.println("NEW BALANCE " + players.getPlayer(target).toString());
			}
			// give money to all players
			else {
				for (i = 0; i < players.getSize(); i++) {
					if(!temp.get(i).equals(currentPlayer)) {
						currentPlayer.subtractBalance(card.getValue());
						players.getPlayer(temp.get(i)).addBalance(card.getValue());
						System.out.println("NEW BALANCE " + currentPlayer.toString());
						System.out.println("NEW BALANCE " + players.getPlayer(temp.get(i)).toString());
					}
				}
			}
			
			break;
		case 4:
			// collect money from a player
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				target = choosePlayer();
				currentPlayer.addBalance(card.getValue());
				players.SubtractBalance(card.getValue(), target);
				System.out.println("NEW BALANCE " + currentPlayer.toString());
				System.out.println("NEW BALANCE " + players.getPlayer(target).toString());
			}
			//collect money from all players
			else {
				for (i = 0; i < players.getSize(); i++) {
					if(!temp.get(i).equals(currentPlayer)) {
						currentPlayer.addBalance(card.getValue());
						players.getPlayer(temp.get(i)).subtractBalance(card.getValue());
						System.out.println("NEW BALANCE " + currentPlayer.toString());
						System.out.println("NEW BALANCE " + players.getPlayer(temp.get(i)).toString());
					}
				}
			}
			break;
			
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
	public int spinWheel () {
		return 1 + (int)(Math.random() * 10);
	}
	
	/**
	 * Function gets specific player entered by current player
	 * @return Player
	 */
	private Player choosePlayer() {
		int option;
		int i;
		int ctr = 1;
		boolean run = true;
		Player target = new Player();
		Scanner scan = new Scanner(System.in);
		ArrayList <Player> temp = players.getPlayers();
		
		for (i = 0; i < temp.size(); i++) {
			
			if(!temp.get(i).equals(currentPlayer)) {
				System.out.println("[ Player " + (i+1) + "]" + temp.get(i).toString());
				ctr ++;
			}
		}
		
		do {
		
			System.out.print("Choose a Player: ");
			option = Integer.parseInt(scan.next());
			System.out.println(option);
			
			switch (option) {
			case 1:
				
				if(!temp.get(option-1).equals(currentPlayer)) {
					target = temp.get(option - 1);
					run = false;
				}
				else
					System.out.println("INVALID PLAYER");
				break;
			case 2:
				if(!temp.get(option-1).equals(currentPlayer)) {
					target = temp.get(option - 1);
					run = false;
				}
				else
					System.out.println("INVALID PLAYER");
				break;
			case 3:
				if(!temp.get(option-1).equals(currentPlayer)) {
					target = temp.get(option - 1);
					run = false;
				}
				else
					System.out.println("INVALID PLAYER");
				break;
			}
			
		}while (run);
		
		return target;
		
	}
}
