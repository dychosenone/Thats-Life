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
	private boolean pay = false;
	private boolean collect =  false;
	
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
		
		gui.disableInputs();
		gml.nextTurn();
		
		do {
			
			gui.updatePlayerInfo(gml.getPlayers(), currentPlayer);
			processTurn();
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
			ActionCard temp = new ActionCard (4, "Birthday");
			ActionCard card = gml.takeActionCard();
			
			takeActionCard (temp);
			
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
		pay = false;
		collect = false;
		System.out.println("TAKEACTIONCARD CONTROLLER ENTERED");
		switch (card.getCardType()) {
		
		case 1: //COLLECT MONEY FROM THE BANK
			gui.displayText("YOU GOT " + card.getCardName());
			gui.displayText("COLLECT " + card.getValue() + " FROM THE BANK");
			
			do {
				System.out.print("");
				
				if (collect) {
					gml.addBalance(currentPlayer, card.getValue());
					gui.displayText(currentPlayer.getName() + "'s NEW BALANCE : " + currentPlayer.getBalance());
				}
				
				if (pay) {
					gui.displayText("INVALID OPTION, COLLECT FROM BANK");
					pay = false;
				}
				
			}while (!collect);
			
			break;
		case 2: //PAY BANK
			gui.displayText("YOU GOT " + card.getCardName());
			gui.displayText("PAY " + card.getValue() + " TO THE BANK");
			
			do {
				System.out.print("");
				
				if (pay) {
					currentPlayer.subtractBalance(card.getValue());
					gui.displayText(currentPlayer.getName() + "'s NEW BALANCE : " + currentPlayer.getBalance());
				}
				
				if (collect) {
					gui.displayText("INVALID OPTION, PAY THE BANK");
					collect = false;
				}
					
			}while (!pay);
			break;
		case 3:
			gui.displayText("YOU GOT " + card.getCardName());
			
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("PAY " + card.getValue() + " TO " + target.getName());
				
				do {
					System.out.print("");
					if(pay) {
						currentPlayer.subtractBalance(card.getValue());					
						target.addBalance(card.getValue());				
						gui.displayText("NEW BALANCE " + target.getBalance());
					}
					
					if (collect) {
						gui.displayText("INVALID OPTION, PAY " +target.getName());
						collect = false;
					}
				
				}while(!pay);
			}
			
			else if(card.getCardName().equalsIgnoreCase("Bonus")) {
				
				gui.displayText("PAY " + card.getValue() + " TO EVERYONE");
				
				do {
					System.out.print("");
					if(pay) {	
						ArrayList<Player> players = gml.getPlayers();
						for (int i = 0; i < players.size(); i++) {
							
							if (!players.get(i).equals(currentPlayer)) {
								currentPlayer.subtractBalance(card.getValue());
								players.get(i).addBalance(card.getValue());
							}
						}
					}
					
					if (collect) {
						gui.displayText("INVALID OPTION, PAY TO EVERYONE" );
						collect = false;
					}
				
				}while(!pay);
				
			}
			break;
		case 4:
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("COLLECT " + card.getValue() + " TO " + target.getName());
				
				do {
					System.out.print("");
					if(collect) {
						currentPlayer.addBalance(card.getValue());					
						target.subtractBalance(card.getValue());				
						gui.displayText("NEW BALANCE " + target.getBalance());
					}
					
					if (pay) {
						gui.displayText("INVALID OPTION, PAY " +target.getName());
						pay = false;
					}
				
				}while(!collect);
			}
			
			else if(card.getCardName().equalsIgnoreCase("Birthday")) {
				
				gui.displayText("COLLECT " + card.getValue() + " TO EVERYONE");
				
				do {
					System.out.print("");
					if(collect) {	
						ArrayList<Player> players = gml.getPlayers();
						for (int i = 0; i < players.size(); i++) {
							
							if (!players.get(i).equals(currentPlayer)) {
								currentPlayer.addBalance(card.getValue());
								players.get(i).subtractBalance(card.getValue());
							}
						}
					}
					
					if (pay) {
						gui.displayText("INVALID OPTION, PAY TO EVERYONE" );
						pay = false;
					}
				
				}while(!collect);
				
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
		
		switch (e.getActionCommand()) {
		
		case "SPIN WHEEL":
			spin = gml.spinWheel();
			break;
		case "PAY":
			pay = true;
			break;
		case "COLLECT":
			collect = true;
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
    	collect = false;
    	pay = false;
    }
}

