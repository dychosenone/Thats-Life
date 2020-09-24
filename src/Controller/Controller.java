package Controller;
import java.awt.event.*;

import java.util.ArrayList;

import Model.GameOfLife;
import Model.Player.Player;
import Model.ActionCard.ActionCard;
import Model.BlueCard.BlueCard;
import View.BoardGUI;
import View.ChooseHouseUI;
import View.ChoosePathUI;
import View.ChoosePlayerUI;
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
	
	private BoardController board;
	
	public Controller (GUI gui, GameOfLife gml) {
		board = new BoardController();
		
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
		System.out.println("1");
		getNumberOfPlayers();
		System.out.println("1");
		
		pauseGUI();
		System.out.println("1");
		getPlayers();
		System.out.println("1");
		
		gui.disableInputs();
		gml.getStarter();
		currentPlayer = gml.getCurrentPlayer();
		gui.updatePlayerInfo(gml.getPlayers());


		
		
		setStartDraw();
		
		do {
			System.out.println(currentPlayer.getName());
			closeLoan ();
			finish = false;
			turn = true; //start turn
			
			do {
				System.out.print("");
				
				if(!finish) {
					
					processTurn();
					gui.updatePlayerInfo(gml.getPlayers());
					
					accessLoan ();
					gui.displayText("FINISH TURN");
					
					finish = true;

				}
				
			}while (turn);
			
			
			if(!gml.gameOver()) {
				do {
					
					gml.nextTurn();
					currentPlayer = gml.getCurrentPlayer();
					
					System.out.println(currentPlayer.getName());
				}while(currentPlayer.isFinish());
				
				gui.nextTurn();
			}
			
		}while (!gml.gameOver());
		
		
		gui.displayText("GAME OVER");
	}
	
	public void getNumberOfPlayers () {
		int temp;
		gui.getNumberOfPlayers();
		
		//enter number of players in game
		while (!done) {
			System.out.print("");
			if (done) {
				
				try {
					
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
				
				catch(Exception e){
					done = false;
					gui.displayText("INVALID INPUT");
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
					
					if (!input.equalsIgnoreCase("")) {
						gui.displayText(input + " has Entered the Game");
						gml.enterPlayers(input);
					}
					
					else {
						gui.displayText("INVALID INPUT");
						done = false;
					}
				}
				
			}while (!done);
			
			pauseGUI();
		}
	}
	
//MAIN GAME METHODS
	
	public void processTurn () {
		System.out.println(currentPlayer.getName() + currentPlayer.isFinish());
		startTurn();
		int i;
		
		gui.displayText("IT IS " + currentPlayer.getName() +"'S TURN" + "\n" + 
		"SPIN THE WHEEL");
		
		do {
			System.out.print("");
			if (spin) {
				gml.wheel = tempWheel;
				gml.wheel = 50; //FOR TESTING
				gml.processTurn();
				gui.displayText("You Rolled a " + gml.getWheel());
				
				for (i = 1; i <= gml.getWheel(); i++) {
					
					/*
					//CHECK IF LAST TILE
					if (gml.isEnd() && i != gml.getWheel()) {
						gui.displayText (currentPlayer.getName() + " is now RETIRED");
						
						i = gml.getWheel();
					}	
					
					if (!currentPlayer.isFinish())*/
						currentPlayer.move();
					
					/*
					//DOUBLE CHECKING IF PLAYER LANDED ON HAS JUMP BUT SPACE IS LAST SPOT INTERACTED ON
					if (i == 1 && currentPlayer.getPosition() != 0) {
						if (gml.isJump())
							currentPlayer.jumpTo(gml.getJump() - 1);
					}*/
					
					/*
					//CHECK IF CURRENT SPACE IS MAGENTA
					if (gml.isMagenta() && i != gml.getWheel() ) {
						int spaceType = gml.interactSpace(currentPlayer.getPosition());
						System.out.println(spaceType);
						gui.interactSpace(spaceType);
						interactSpace (spaceType);
					}*/
					
					/*
					//CHECK IF SPACE HAS JUMP
					if(gml.isJump()){
						int spaceType = gml.interactSpace(currentPlayer.getPosition());
						System.out.println("Jumped to space number: " + gml.getJump());
						gui.interactSpace(spaceType);
						interactSpace(spaceType);
						if (i != gml.getWheel()) 
							currentPlayer.jumpTo(gml.getJump() - 1);
					}*/

					//CHECK IF LAST TILE
				}
				
				if(!currentPlayer.isFinish()) {
					gui.interactSpace(gml.interactSpace(currentPlayer.getPosition()));
					interactSpace (gml.interactSpace(currentPlayer.getPosition()));
				}
				
				
			}
			
			//currentPlayer.position = 0; //FOR TESTING
			
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
		case 4:
			haveChild ();
			break;
		case 5:
			buyHouse();
			break;
		case 6:
			takeBlueCard();
			break;
		case 7:
			greenSpaceEffect ();
			break;
		}
	}

//ORANGE SPACES
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
				
				Player target = choosePlayer(1);
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+	currentPlayer.getName() + ": -" + card.getValue() + "\n"
						+ target.getName() + ": + " + card.getValue());
				
				currentPlayer.subtractBalance(card.getValue());					
				target.addBalance(card.getValue());				
			}
			
			else if(card.getCardName().equalsIgnoreCase("Bonus")) {
				ArrayList<Player> players = gml.getPlayers();

				gml.payEveryone(card.getValue());
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": -" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : +" + card.getValue());
			}
				break;
		
		case 4:
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				Player target = choosePlayer(2);
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						 + currentPlayer.getName() + ": +" + card.getValue() + "\n"
						 + target.getName() + ": - " + card.getValue());
				
				currentPlayer.addBalance(card.getValue());					
				target.subtractBalance(card.getValue());				
			}
			
			else if(card.getCardName().equalsIgnoreCase("Birthday")) {
				ArrayList<Player> players = gml.getPlayers();
				
				gml.collectFromEveryone(10000);
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": +" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : -" + card.getValue());
			}
			break;
		}
		
		gui.disableInputs();
	}
	
	public Player choosePlayer(int situation) {
		int index = 0;
		ChoosePlayerUI chooseUI = new ChoosePlayerUI(situation);
		ChoosePlayerController chooseCont = new ChoosePlayerController (chooseUI, gml.getPlayers(), currentPlayer);
		
		boolean run = true;
		
		do {
			System.out.print("");
			
			switch (chooseCont.getChoice()) {
			case 0:
				index = 0;
				run = false;
				break;
			case 1:
				index = 1;
				run = false;
				break;
			case 2:
				index = 2;
				run = false;
				break;
			default:
				run = true;
				
			}
		}while (run);
		
		return gml.getPlayers().get(index);
	}
	
//BLUE SPACES
	public void takeBlueCard(){
		BlueCard card = gml.takeBlueCard();
		int amount = gml.blueCardEffect(card);
		
		
		if(card.checkPlayerCareer(currentPlayer.getJob()) == true) {
			gml.addBalance(currentPlayer, amount);
			gui.displayText("YOU GOT " + card.getCardName() + ": +" + amount);
		} 
		else {
			gui.displayText("YOU GOT " + card.getCardName() + ": -" + amount);
		}
	}
//GREEN SPACES
	public void greenSpaceEffect() {
		int temp = 1 + (int)(Math.random() * 10);
		
		if (temp > 6) {
			int raise = gml.payRaise();
			switch (raise) {
			case -1:
				gui.displayText("REACHED MAXIMUM NUMBER OF PAY RAISES");
				gml.payDay();
				break;
			default:
				gui.displayText("SALARY IS INCREASED BY " + raise);
				break;
			}
		}
		else {
			gml.payDay();
			gui.displayText("PAYDAY! : +" + (currentPlayer.getJob().getSalary() - currentPlayer.getJob().getTax()));
		}
	}
//MAGENTA SPACES
	
	public void getMarried () {
		spin = false;
		gui.displayText("SPIN WHEEL1");
		
		do {
			System.out.print("");
			if (spin) {
				if (tempWheel  % 2 == 0) {// if wheel is even
					gui.displayText("YOU ROLLED A " + tempWheel + " COLLECT 10000 FROM EVERYONE" );
				}
				
				else {
					gui.displayText("YOU ROLLED A " + tempWheel + " COLLECT 5000 FROM EVERYONE" );
					gml.collectFromEveryone(5000);
				}
			}
			
			
		}while (!spin);
		gml.getMarried(tempWheel);
		currentPlayer.getMarried();
	}
	
	public void choosePath () {
		ChoosePathController cCont;
		ChoosePathUI cUI = new ChoosePathUI("START CAREER", "START COLLEGE");
		
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
	
	public void haveChild () {
		int tempChance = GameOfLife.spinWheel();
		if (!currentPlayer.isMarried()) {
			if (tempChance > 6) {// have twins
				if(currentPlayer.haveBabies(2)) {
					gui.displayText("YOU HAVE TWINS!");
					gml.collectFromEveryone(10000);
				}
				else {
					gui.displayText("YOU CAN'T HAVE ANYMORE BABIES");
				}
			}
			else {// have baby
				if(currentPlayer.haveBabies(1)) {
					gui.displayText("YOU HAVE A BABY!");
					gml.collectFromEveryone(5000);
				}
				else {
					gui.displayText("YOU CAN'T HAVE ANYMORE BABIES");
				}
			}
		}
		else {
			gui.displayText("YOU ARE NOT MARRIED");
		}
	}
	
	public void buyHouse () {
		ChooseHouseUI houseUI = new ChooseHouseUI ();
		ChooseHouseController houseCont = new ChooseHouseController (houseUI, gml.getHouseCards());
		
		boolean run = true;
		int index = -1;
		
		do {
			System.out.print("");
			
			switch (houseCont.getOption()){
				case 0: case 1: case 2: case 3: case 4: case 5:
					index = houseCont.getOption();
					run = false;
					break;
			}
		}while (run);
		
		gml.buyHouse(index);
	}


// BoardGUI Draw
	public void setStartDraw () {
		
		int numPlayers = gml.getPlayers().size();
		SpaceController space;
		BoardGUI playingBoard;
		
		for(int i = 1; i <= numPlayers; i++) {
			switch(i) {
				case 1:
					space = board.getPlayerSpacePosition(1, 0);
					playingBoard = gui.getBoard();
					playingBoard.showPlayer(playingBoard.getGraphics(), space.getX(), space.getY(), 1);
					break;
				case 2:
					space = board.getPlayerSpacePosition(2, 0);
					playingBoard = gui.getBoard();
					playingBoard.showPlayer(playingBoard.getGraphics(),space.getX(), space.getY(), 2);
					break;
				case 3:
					space = board.getPlayerSpacePosition(3, 0);
					playingBoard = gui.getBoard();
					playingBoard.showPlayer(playingBoard.getGraphics(), space.getX(), space.getY(), 3);
					break;
			}
		}
		
	}
	
	
//ACTION LISTENERS	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		ArrayList <Player> players = gml.getPlayers();
		switch (e.getActionCommand()) {
		
		case "SPIN WHEEL":
			tempWheel = GameOfLife.spinWheel();
			spin = true;
			break;
		case "GET LOAN":
			if (access) {
				gui.displayText("YOU BORROWED 20K FROM BANK");
				gml.getLoan();
				gui.updatePlayerInfo(players);
			}
			else {
				gui.displayText("SPIN WHEEL FIRST");
			}
			break;
		case "PAY LOAN":
			if (access) {
				if (gml.payLoan()) {
					gui.displayText("YOU PAID DEBT!");
					gui.updatePlayerInfo(players);
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

