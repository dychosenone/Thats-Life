package Controller;
import java.awt.event.*;
import java.util.ArrayList;

import Model.GameOfLife;
import Model.Player.Player;
import Model.Space;
import Model.ActionCard.ActionCard;
import View.ChoosePath;
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
	private boolean access = false;
	private boolean finish = false;
	
	private int path = 0;
	private int tempWheel;
	
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
		gml.printSpaces();
		System.out.println("CONTROLLER STARTS");
		getNumberOfPlayers();
		
		pauseGUI();
		getPlayers();
		
		int i = 0;
		
		gui.disableInputs();
		gml.nextTurn();
		gui.updatePlayerInfo(gml.getPlayers(), currentPlayer);
		do {
			closeLoan ();
			finish = false;
			turn = true; //start turn
			
			do {
				System.out.print("");
				
				if(!finish) {
					processTurn();
					gui.updatePlayerInfo(gml.getPlayers(), currentPlayer);
					accessLoan ();
					gui.displayText("FINISH TURN");
					
					finish = true;

				}
				
			}while (turn);
			gui.nextTurn();
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
					maxPlayers = temp;
					gml.getNumberOfPlayers(maxPlayers);
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
	
//MAIN GAME METHODS
	
	public void processTurn () {
		startTurn();
		int i;
		currentPlayer = gml.getCurrentPlayer();
		
		gui.displayText("IT IS " + currentPlayer.getName() +"'S TURN" + "\n" + 
		"SPIN THE WHEEL");
		
		do {
			System.out.print("");
			if (spin) {
				gml.wheel = tempWheel;
				gml.processTurn();
				gui.displayText("You Rolled a " + gml.getWheel());
				
				for (i = 1; i <= gml.getWheel(); i++) {
					
					currentPlayer.move();
					System.out.println(gml.isMagenta());
					if (gml.isMagenta() && i != gml.getWheel() ) {
						System.out.println(gml.isMagenta() + "2:" + gml.getWheel() + " " + i + ":" +gml.getWheel());
						int spaceType = gml.interactSpace(currentPlayer.getPosition());
						System.out.println(spaceType);
						gui.interactSpace(spaceType);
						interactSpace (spaceType);
					}
				}		
				System.out.println(gml.isMagenta() + "1:" + gml.getWheel() + " " + i + ":" +gml.getWheel());
				gui.interactSpace(gml.interactSpace(currentPlayer.getPosition()));
				interactSpace (gml.interactSpace(currentPlayer.getPosition()));
				
				
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
			if (!currentPlayer.isMarried()) {
				getMarried ();
				gui.displayText("YOU ARE NOW MARRIED");
			}
			else {
				gui.displayText("YOU ARE CURRENTLY MARRIED");
			}
			break;
		case 3:
			choosePath();
			break;
		}
	}

//ORANGE SPACE
	public void takeActionCard (ActionCard card) {
		System.out.println("TAKEACTIONCARD CONTROLLER ENTERED");
		switch (card.getCardType()) {
		
		case 1: //COLLECT MONEY FROM THE BANK
			
			gui.displayText("YOU GOT " + card.getCardName() + "\n" 
							+ currentPlayer.getName() + ": +" + card.getValue());
			
			gml.addBalance(currentPlayer, card.getValue());
			
			break;
		case 2: //PAY BANK
			
			gui.displayText("YOU GOT " + card.getCardName() + "\n" 
							+ currentPlayer.getName() + ": -" + card.getValue());
			
			gml.subtractBalance(currentPlayer, card.getValue());
			
			break;
		case 3:
			
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+	currentPlayer.getName() + ": -" + card.getValue() + "\n"
						+ target.getName() + ": + " + card.getValue());
				
				currentPlayer.subtractBalance(card.getValue());					
				target.addBalance(card.getValue());				
			}
			
			else if(card.getCardName().equalsIgnoreCase("Bonus")) {
				
				int i;
				ArrayList<Player> players = gml.getPlayers();

				for (i = 0; i < players.size(); i++) {	

					if (!players.get(i).equals(currentPlayer)) {
						currentPlayer.subtractBalance(card.getValue());
						players.get(i).addBalance(card.getValue());
					}
				}
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": -" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : +" + card.getValue());
			}
				break;
		
		case 4:
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				Player target = choosePlayer();
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						 + currentPlayer.getName() + ": +" + card.getValue() + "\n"
						 + target.getName() + ": - " + card.getValue());
				
				currentPlayer.addBalance(card.getValue());					
				target.subtractBalance(card.getValue());				
			}
			
			else if(card.getCardName().equalsIgnoreCase("Birthday")) {
				
				ArrayList<Player> players = gml.getPlayers();
				
				for (int i = 0; i < players.size(); i++) {
					
					if (!players.get(i).equals(currentPlayer)) {
						currentPlayer.addBalance(card.getValue());
						players.get(i).subtractBalance(card.getValue());
					}
				}
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": +" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : -" + card.getValue());
			}
			break;
		}
	}
	
//MAGENTS SPACES
	
	public void getMarried () {
		spin = false;
		gui.displayText("SPIN WHEEL1");
		
		do {
			System.out.print("");
			if (spin) {
				if (tempWheel  % 2 == 0) {// if wheel is even
					gui.displayText("YOU ROLLED A " + tempWheel + " COLLECT 10000 FROM EVERYONE" );
					gml.getMarried(tempWheel);
				}
				
				else {
					gui.displayText("YOU ROLLED A " + tempWheel + " COLLECT 5000 FROM EVERYONE" );
					gml.getMarried(tempWheel);
				}
			}
			
		}while (!spin);
	}
	
	public void choosePath () {
		ChoosePathController cCont;
		ChoosePath cUI = new ChoosePath("START CAREER", "START COLLEGE");
		
		if (currentPlayer.getPosition() == 1) {
			cCont = new ChoosePathController ("START CAREER", "START COLLEGE", cUI);
			boolean run = true;
			
			do {
				
				System.out.print(cCont.getChoice());
				
				switch (cCont.getChoice()) {
				case 0:
					run = true;
					break;
				case 1:
					run = false;
					break;
				case 2:
					run = false;
					break;
				}
				
			}while(run);
			path = cCont.getChoice();
			System.out.println(path);
		}
		
		else if (currentPlayer.getPosition() == 20) {
			cCont = new ChoosePathController ("FAMILY PATH", "CAREER PATH", cUI);
			boolean run = true;
			
			do {
				
				System.out.print(cCont.getChoice());
				
				switch (cCont.getChoice()) {
				case 0:
					run = true;
					break;
				case 1:
					run = false;
					break;
				case 2:
					run = false;
					break;
				}
				
			}while(run);
			path = cCont.getChoice();
			System.out.println(path);
		}
	}
	
//ACTION LISTENERS	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		ArrayList <Player> players = gml.getPlayers();
		switch (e.getActionCommand()) {
		
		case "SPIN WHEEL":
			tempWheel = gml.spinWheel();
			spin = true;
			break;
		case "GET LOAN":
			if (access) {
				gui.displayText("YOU BORROWED 20K FROM BANK");
				gml.getLoan();
				gui.updatePlayerInfo(players, currentPlayer);
			}
			else {
				gui.displayText("SPIN WHEEL FIRST");
			}
			break;
		case "PAY LOAN":
			if (access) {
				if (gml.payLoan()) {
					gui.displayText("YOU PAID DEBT!");
					gui.updatePlayerInfo(players, currentPlayer);
				}
				else {
					gui.displayText("YOU HAVE NO DEBT!");
				}
			}
			
			else {
				gui.displayText("FINISH TURN FIRST");
			}
			break;
		case "END TURN":
			if (finish)
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
    
    public void accessLoan () {
    	access = true;
    }
    
    public void closeLoan () {
    	access = false;
    }
}

