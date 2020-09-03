import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GameOfLife {
	
	private static final int MINPLAYERS = 2;
	private static final int MAXPLAYERS = 3;
	
	private ActionCardDeck actionDeck;
	
	private Players players = new Players ();
	private Player currentPlayer = new Player();
	private int wheel;

	private boolean turn = false;	
	private boolean gameOver = false;

	
	/**
	 * When a new GameOfLife object is created, it generates a new ActionCardDeck.
	 */
	
	GameOfLife (){
		actionDeck = new ActionCardDeck();

	}
	
	/**
	 * This function gets the number of players who will be playing then does a loop to add a new Player class to the
	 * Players collection.
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
			System.out.print("Enter Number of Players: ");
			num = Integer.parseInt (scan.nextLine());
			
			if (num >= MINPLAYERS && num <= MAXPLAYERS) {
				run = false;
			}
			
		}while (run);
		
		return num;
	}
	
	/**
	 * Function sets all actions of players.
	 */
	
	public void processTurn (){
		turn = true;
		int option;
		boolean done = false;
		Scanner scan = new Scanner(System.in);
		
		System.out.println(currentPlayer.getName() + " is Now Playing");
		System.out.println("Career: " + currentPlayer.getJob().getPosition());
		wheel = spinWheel ();
		System.out.println("You rolled a " + wheel);
	
		do {
			
			System.out.print("[1] Show All Action Cards\n"
					+ "[2] Take an action Card\n"
					+ "[3] End Turn\n");
			
			System.out.print("Input Option 1 or 2 or 3: ");
			option = Integer.parseInt(scan.next());
			
			switch (option) {
				
				case 1:
					System.out.println("You pressed Option 1");
					actionDeck.showAllCards();
					break;
				case 2:
					
					if (!done) {
						System.out.println("You pressed Option 2");
						takeActionCard ();
						done = true;
					}
					else
						System.out.println("You've taken an Action Card Already");
					break;
				case 3:
					System.out.println("You pressed Option 3");
					
					if (done)
						turn = false;
					
					else
						System.out.println("You need to take an Action Card");
					break;
			}
		}while(turn);
	}
	
	/**
	 * Function takes an Action Card if current player lands on Orange Space.
	 */
	
	public void takeActionCard () {
		int i;
		ActionCard tempcard = actionDeck.getCard();
		ActionCard card = actionDeck.takeCard();
		
		ArrayList <Player> temp = players.getPlayers();
		
		Player target;
		
		System.out.println(card.toString());
		System.out.println("CURRENT BALANCE " + players.getPlayer(currentPlayer).toString());

		switch (card.getCardType()) {
		case 1:
			// collects money from bank
			players.AddBalance(card.getValue(), currentPlayer);
			System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
			break;
		case 2:
			// give money to bank
			players.SubtractBalance(card.getValue(), currentPlayer);
			System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
			break;
		case 3:
			
			// give money to a player
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				target = choosePlayer();
				players.SubtractBalance(card.getValue(), currentPlayer);
				players.AddBalance(card.getValue(), target);
				System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
				System.out.println("NEW BALANCE " + players.getPlayer(target).toString());
			}
			// give money to all players
			else {
				for (i = 0; i < players.getSize(); i++) {
					if(!temp.get(i).equals(currentPlayer)) {
						players.SubtractBalance(card.getValue(), currentPlayer);
						players.getPlayer(temp.get(i)).addBalance(card.getValue());
						System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
						System.out.println("NEW BALANCE " + players.getPlayer(temp.get(i)).toString());
					}
				}
			}
			
			break;
		case 4:
			// collect money from a player
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				target = choosePlayer();
				players.AddBalance(card.getValue(), currentPlayer);
				players.SubtractBalance(card.getValue(), target);
				System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
				System.out.println("NEW BALANCE " + players.getPlayer(target).toString());
			}
			//collect money from all players
			else {
				for (i = 0; i < players.getSize(); i++) {
					if(!temp.get(i).equals(currentPlayer)) {
						players.AddBalance(card.getValue(), currentPlayer);
						players.getPlayer(temp.get(i)).subtractBalance(card.getValue());
						System.out.println("NEW BALANCE " + players.getPlayer(currentPlayer).toString());
						System.out.println("NEW BALANCE " + players.getPlayer(temp.get(i)).toString());
					}
				}
			}
			break;
			
		}
	}


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
		Player target = new Player ();
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
