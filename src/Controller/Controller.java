package Controller;
import java.awt.event.*;
import java.util.ArrayList;

import Model.GameOfLife;
import Model.Player.Player;
import Model.Space;
import Model.ActionCard.ActionCard;
import View.GUI;

public class Controller implements ActionListener, KeyListener{
	
	private GameOfLife gml;
	private GUI gui;
	private String input;
	private int maxPlayers;
	private Player currentPlayer;
	
	private boolean done = false;
	private boolean spin = false;
	private boolean turn = true;
	
	public Controller (GUI gui, GameOfLife gml) {
		this.gui = gui;
		this.gml = gml;
		
		gui.setListener(this);
		gui.setKeyListener(this);
		
		input ="";
		currentPlayer = new Player ();
	}
	
	
//GAME INTIIALIZING
	public void startGame () {
		//gets number of players
		System.out.println("CONTROLLER STARTS");
		getNumberOfPlayers();
		
		pauseGUI();
		getPlayers();
		
		int i = 0;
		boolean finish = false;
		
		gui.disableInputs();
		gml.nextTurn();
		gui.updatePlayerInfo(gml.getPlayers(), currentPlayer);
		do {
			finish = false;
			turn = true; //start turn
			
			do {
				System.out.print("");
				
				if(!finish) {
					processTurn();
					gui.updatePlayerInfo(gml.getPlayers(), currentPlayer);
					finish = true;
				}
				
			}while (turn);
			
			gml.nextTurn();
			
		}while (i < 50);
		
	}
	
	public void getNumberOfPlayers () {
		int temp;
		gui.getNumberOfPlayers();
		
		//enter number of players in game
		while (!done) {
			System.out.print("");
			if (done) {
				temp = Integer.parseInt(input);
				
				if(temp >= 2 && temp <= 3) {
					gml.getNumberOfPlayers(maxPlayers);
					maxPlayers = temp;
				}
				
				else {
					gui.displayText("INVALID INPUT, ONLY 2 TO 3 PLAYERS ARE ALLOWED");
					gui.getNumberOfPlayers();
					pauseGUI();
				}
				
			}
		}
	}
	
	public void getPlayers () {
		
		for (int i = 0; i < maxPlayers; i++) {
				
			gui.inputPlayers(i);
			//enters players in game
			do {
				System.out.print("");
				if(done) {
					gui.displayText(input + " has Entered the Game");
					gml.enterPlayers(input);
				}
				
			}while (!done);
			
			pauseGUI();
		}
	}
	
//MAIN GAME METHODS
	
	public void processTurn () {
		startTurn();
		currentPlayer = gml.getCurrentPlayer();
		
		gui.displayText("IT IS " + currentPlayer.getName() +"'S TURN");
		gui.displayText("SPIN THE WHEEL");
		do {
			System.out.print("");
			if (spin) {
				gml.processTurn();
				
				gui.displayText("You Rolled a " + gml.getWheel());
				
				for (int i = 1; i <= gml.getWheel(); i++) {
					
					currentPlayer.move();
					
					Space space = gml.getSpace();
					
					if (gml.isMagenta(space) && i != gml.getWheel() ) {
						
						int spaceType = gml.interactSpace(currentPlayer.getPosition());
						System.out.println(spaceType);
						gui.interactSpace(spaceType);
						interactSpace (spaceType);
					}
				}			
				
				gui.interactSpace(gml.interactSpace(currentPlayer.getPosition()));
				interactSpace (gml.interactSpace(currentPlayer.getPosition()));
				
				gui.displayText(currentPlayer.getName() + "'s FINAL POSITION : " + currentPlayer.getPosition());
				gui.displayText("");
				
			}
			
		}while (!spin);
	}
	
	
	public void interactSpace (int spaceType) {
		//ORANGESPACES
		switch (spaceType) {
		case 0: //COLLECT ACTION CARD
			ActionCard card = gml.takeActionCard();
			
			takeActionCard (card);
			
			break;
		case 1:
			gml.jobSearch();
			break;
		case 2:
			gui.displayText("YOU ARE NOW MARRIED");
			break;
		}
	}
	
	public void takeActionCard (ActionCard card) {
		System.out.println("TAKEACTIONCARD CONTROLLER ENTERED");
		switch (card.getCardType()) {
		
		case 1: //COLLECT MONEY FROM THE BANK
			gui.displayText("YOU GOT " + card.getCardName());
			gui.displayText("COLLECT " + card.getValue() + " FROM THE BANK");
			
			gml.addBalance(currentPlayer, card.getValue());
			
			
			break;
		case 2: //PAY BANK
			gui.displayText("YOU GOT " + card.getCardName());
			gui.displayText("PAY " + card.getValue() + " TO THE BANK");
			
			currentPlayer.subtractBalance(card.getValue());
			
			break;
		case 3:
			gui.displayText("YOU GOT " + card.getCardName());
			
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("PAY " + card.getValue() + " TO " + target.getName());
				
				currentPlayer.subtractBalance(card.getValue());					
				target.addBalance(card.getValue());				
			}
			
			else if(card.getCardName().equalsIgnoreCase("Bonus")) {
				
				int i;
				ArrayList<Player> players = gml.getPlayers();
				
				gui.displayText("PAY " + card.getValue() + " TO EVERYONE");

				for (i = 0; i < players.size(); i++) {	

					if (!players.get(i).equals(currentPlayer)) {
						currentPlayer.subtractBalance(card.getValue());
						players.get(i).addBalance(card.getValue());
					}
				}
			}
				break;
		
		case 4:
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("COLLECT " + card.getValue() + " TO " + target.getName());
				
				currentPlayer.addBalance(card.getValue());					
				target.subtractBalance(card.getValue());				
				gui.displayText("NEW BALANCE " + target.getBalance());
			}
			
			else if(card.getCardName().equalsIgnoreCase("Birthday")) {
				
				gui.displayText("COLLECT " + card.getValue() + " TO EVERYONE");
				ArrayList<Player> players = gml.getPlayers();
				
				for (int i = 0; i < players.size(); i++) {
					
					if (!players.get(i).equals(currentPlayer)) {
						currentPlayer.addBalance(card.getValue());
						players.get(i).subtractBalance(card.getValue());
					}
				}	
			}
			break;
		}
	}
	
	public Player choosePlayer() {
		gui.enableInputs();
		int option;
		int index = -1;
		
		ArrayList <Player> players = gml.getPlayers();
		
		gui.choosePlayer(players, currentPlayer);
		
		do {
			System.out.print("");
			
			if (done) {
				option = Integer.parseInt(input);
				
				index  = gml.choosePlayer (option);
				
				switch (index) {
				
				case -1:
					gui.displayText("INVALID PLAYER / INVALID INPUT, TRY AGAIN");
					done = false;
					break;
				case 0: case 1: case 2: 
					break;
				}
				
			}
		}while (!done);
		
		pauseGUI();
		return players.get(index);
	}
	
//ACTION LISTENERS	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		ArrayList <Player> players = gml.getPlayers();
		switch (e.getActionCommand()) {
		
		case "SPIN WHEEL":
			spin = gml.spinWheel();
			break;
		case "GET LOAN":
			gui.displayText("YOU BORROWED 20K FROM BANK");
			gml.getLoan();
			gui.updatePlayerInfo(players, currentPlayer);
			break;
		case "PAY LOAN":
			if (gml.payLoan()) {
				gui.displayText("YOU PAID DEBT!");
				gui.updatePlayerInfo(players, currentPlayer);
			}
			
			else {
				gui.displayText("YOU HAVE NO DEBT!");
			}
			break;
		case "END TURN":
			turn = false;
			break;
		}
	}
	
	
//KEY LISTENERS
    @Override
	public void keyPressed(KeyEvent e) {
    	int code = e.getKeyCode();
    	
    	if (code == 10) {
    		input = gui.getInput();
    		done = true;
    	}
	}
    
    public void keyTyped (KeyEvent e) {
    	
    }
    
    public void keyReleased (KeyEvent e) {
    	
    }
    
 
//GUI METHODS
    public void pauseGUI() {
    	done = false;
    }
    
    public void startTurn () {
    	spin = false;
    }
}

