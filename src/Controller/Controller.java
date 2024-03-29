package Controller;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Model.GameOfLife;
import Model.Player.Player;
import Model.SalaryCard.SalaryCard;
import Model.ActionCard.ActionCard;
import Model.BlueCard.BlueCard;
import Model.Career.CareerCard;
import View.ActionCardUI;
import View.CareerUI;
import View.ChooseCareerUI;
import View.ChooseHouseUI;
import View.ChoosePathUI;
import View.ChoosePlayerUI;
import View.ConsoleUI;
import View.GUI;
import View.InputDialog;
import View.JobSearchUI;

public class Controller implements ActionListener{
	
	private GameOfLife gml;
	private GUI gui;
	private int maxPlayers;
	private Player currentPlayer;
	
	private boolean spin = false;
	private boolean turn = true;
	private boolean access = false;
	private boolean finish = false;
	
	private boolean over = false;
	
	private int tempWheel;

	private int currentPlayerID;
	
	private BoardController board;
	
	public Controller (GUI gui, GameOfLife gml) {
		board = new BoardController();
		
		this.gui = gui;
		this.gml = gml;
		
		gui.setListener(this);
		
		currentPlayer = new Player ();
		currentPlayerID = 1;
	}
	
	
//GAME INTIIALIZING
	public void startGame () {
		//gets number of players
		
		gml.printSpaces();
		getNumberOfPlayers();
		
		getPlayers();
		currentPlayerID = 1;
		
		gml.getStarter();
		currentPlayer = gml.getCurrentPlayer();
		gui.updatePlayerInfo(gml.getPlayers());

		// Function sets position of Player to 0 (GUI)
		setStartDraw();

		do {
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
					
					if(currentPlayerID == gml.getPlayers().size()){
						currentPlayerID = 1;
					} else {
						currentPlayerID++;
					}
					
					
					
				}while(currentPlayer.isFinish());
				
				gui.nextTurn();
			}
			
		}while (!gml.gameOver());

		gui.displayText("GAME OVER");
		decideWinner (gml.decideWinner());
	}
	
	public void getNumberOfPlayers () {
		int temp;
		boolean run = true;

		//enter number of players in game		
		InputDialog inputUI = new InputDialog ();
		InputController inputCont = new InputController(inputUI, "ENTER NUMBER OF PLAYERS");
		
		do {
			System.out.print("");
			
			switch (inputCont.getStatus()) {
			case -1:
				inputUI = new InputDialog ();
				inputCont = new InputController(inputUI, "ENTER NUMBER OF PLAYERS");
				break;
			case 1:
				try {
					temp = Integer.parseInt(inputCont.getInput());
					
					if(temp >= 2 && temp <= 3) {
						maxPlayers = temp;
						gml.getNumberOfPlayers(maxPlayers);
						run = false;
					}
					else {
						inputUI = new InputDialog ();
						inputCont = new InputController(inputUI, "ENTER NUMBER OF PLAYERS");
					}
				}
				catch (Exception e) {
					displayConsole ("INVALID INPUT!");
					inputUI = new InputDialog ();
					inputCont = new InputController(inputUI, "ENTER NUMBER OF PLAYERS");
				}
				break;
			}
			
		}while (run);
	}
	
	public void getPlayers () {
		String input;
		boolean run = true;
		InputDialog inputUI;
		InputController inputCont;

		//enter number of players in game
		
		int i;
		
		for (i = 0; i < maxPlayers; i++) {
			run = true;
			inputUI = new InputDialog ();
			inputCont = new InputController(inputUI, "ENTER NAME OF PLAYER " + (i+1));
			
			do {
				System.out.print("");
				
				switch (inputCont.getStatus()) {
				case -1:
					inputUI = new InputDialog ();
					inputCont = new InputController(inputUI, "ENTER NAME OF PLAYER " + (i+1));
					break;
				case 1:
					input = inputCont.getInput();
					System.out.print(input);
					
					if (!input.equalsIgnoreCase("") && !checkSameName (input)) {
						System.out.print(input);
						gml.enterPlayers(input);
						gui.updatePlayerInfo(gml.getPlayers());
						
						run = false;
					}
					
					else {
						displayConsole("INVALID INPUT");
						inputUI = new InputDialog ();
						inputCont = new InputController(inputUI, "ENTER NAME OF PLAYER " + (i+1));
					}
				}
				
			}while (run);
		}
	}
	
	public boolean checkSameName (String name) {
		int i;
		System.out.print(name);
		
		for (i = 0; i < gml.getPlayers().size(); i++) {
			if(name.equalsIgnoreCase(gml.getPlayers().get(i).getName())) {
				return true;
			}
		}
		
		return false;
	}
	
//MAIN GAME METHODS
	
	public void processTurn () {
		startTurn();
		int i;
		
		gui.displayText("IT IS " + currentPlayer.getName() +"'S TURN" + "\n" + 
		"SPIN THE WHEEL");
		
		do {
			boolean didJump = false;
			System.out.print("");
			if (spin) {
				gml.wheel = tempWheel;
				//gml.wheel = 100; //FOR TESTING

				gui.displayDice(gml.getWheel());
				
				for (i = 0; i < gml.getWheel(); i++) {
					System.out.println("Current Position: " + currentPlayer.getPosition());
					
					// check if first tile
					if (currentPlayer.getPosition() == 0) {
						int position = choosePath();
						currentPlayer.setPosition(position);
					}
					//Check if Choose Path 46
					else if(currentPlayer.getPosition() == 46){
						int position = choosePath();
						currentPlayer.setPosition(position);
					}
					//MOVE
					else if (!currentPlayer.isFinish())
						currentPlayer.move();

					//CHECK IF LAST TILE
					if (gml.isEnd() && i != gml.getWheel()) {
						displayConsole (currentPlayer.getName() + " IS NOW RETIRED");
						movePlayer(this.currentPlayerID, currentPlayer.position);
						i = gml.getWheel();
					}
					
					
					//CHECK IF CURRENT SPACE IS MAGENTA
					if (gml.isMagenta() &&  i != gml.getWheel()) {
						movePlayer(this.currentPlayerID, currentPlayer.position);
						int spaceType = gml.interactSpace(currentPlayer.getPosition());
						gui.interactSpace(spaceType);
						interactSpace (spaceType);
					}

					//DOUBLE CHECKING IF PLAYER LANDED ON HAS JUMP BUT SPACE IS LAST SPOT INTERACTED ON
					if (i == 1 && currentPlayer.getPosition() != 0) {
						if (gml.isJump())
							currentPlayer.setPosition(gml.getJump()-1);
					}

					//CHECK IF SPACE HAS JUMP
					if(gml.isJump() == true){
						if (i != gml.getWheel()) {
							didJump = true;
							currentPlayer.jumpTo(gml.getJump()-1);
						}
					}
				}

				//CHECK IF LAST TILE
				if(!currentPlayer.isFinish() && !didJump) {
					gui.interactSpace(gml.interactSpace(currentPlayer.getPosition()));
					interactSpace (gml.interactSpace(currentPlayer.getPosition()));
				}
			}
			
			//currentPlayer.position = 0; //FOR TESTING

			
		}while (!spin);

		if(currentPlayer.position != 133)
			movePlayer(this.currentPlayerID, currentPlayer.position);
	}
	
	
	public void interactSpace (int spaceType) {
		//ORANGESPACES
		System.out.println(spaceType);
		System.out.println("INTERACTING SPACE");
		switch (spaceType) {
		
		case 0: //COLLECT ACTION CARD
			ActionCard card = gml.takeActionCard();
			takeActionCard (card);
			break;
		case 1:
			jobSearch();
			break;
		case 2:
			if (currentPlayer.isMarried() == false) {
				getMarried ();				
				displayConsole ("YOU ARE NOW MARRIED");
				
			}
			else {
				displayConsole ("YOU ARE CURRENTLY MARRIED");		
			}
			break;
		case 3:
			// choosePath();
			break;
		case 4:
			haveChild ();
			break;
		case 5:
			buyHouse();
			break;
		case 6:
			graduate ();
			break;
		case 7:
			collegeCareerChoice ();
			break;
		case 8:
			takeBlueCard();
			break;
		case 9:
			greenSpaceEffect ();
			break;
		}
		
		gui.updatePlayerInfo(gml.getPlayers());
	}
	
	public int endGame () {
		gui.dispose();
		return 0;
	}
	
	public void decideWinner (Player winner) {
		
		displayConsole ("WINNER IS " + winner.getName());
		
		over = true;
		gui.dispose();
	}
	
	public void displayConsole (String message) {
		
		ConsoleUI tempUI = new ConsoleUI ();
		ConsoleController tempCont = new ConsoleController (tempUI, message);
		boolean run = true;
		
		do {
			System.out.print("");
			
			switch (tempCont.getStatus()) {
			case 1:
				run = false;
				break;
			}
		}while (run);
	}

//ORANGE SPACES
	public void takeActionCard (ActionCard card) {
		System.out.println("TAKEACTIONCARD CONTROLLER ENTERED");
		switch (card.getCardType()) {
		
		case 1: //COLLECT MONEY FROM THE BANK
			
			displayCard(card, "COLLECT FROM THE BANK");
			
			gui.displayText("YOU GOT " + card.getCardName() + "\n" 
							+ currentPlayer.getName() + ": +" + card.getValue());
			
			gml.addBalance(currentPlayer, card.getValue());
			
			break;
		case 2: //PAY BANK
			
			displayCard(card, "PAY THE BANK");
			
			gui.displayText("YOU GOT " + card.getCardName() + "\n" 
							+ currentPlayer.getName() + ": -" + card.getValue());
			
			gml.subtractBalance(currentPlayer, card.getValue());
			
			break;
		case 3:
			
			if (card.getCardName().equalsIgnoreCase("Lawsuit")) {
				
				displayCard(card, "PAY TO A PLAYER");
				
				if (gml.getNumOfRetiredPlayers() != (gml.getPlayersSize() -1)) {
					Player target = choosePlayer(1);
					
					currentPlayer.subtractBalance(card.getValue());					
					target.addBalance(card.getValue());				
				}		
			}
			
			else if(card.getCardName().equalsIgnoreCase("Bonus")) {
				ArrayList<Player> players = gml.getPlayers();
				
				displayCard(card, "PAY TO EVERYONE");

				gml.payEveryone(card.getValue());
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": -" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : +" + card.getValue());
			}
				break;
		
		case 4:
			if (card.getCardName().equalsIgnoreCase("FileLawsuit")) {
				
				displayCard(card, "COLLECT FROM A PLAYER");
				
				if (gml.getNumOfRetiredPlayers() != (gml.getPlayersSize() - 1)) {
					Player target = choosePlayer(2);
					
					currentPlayer.addBalance(card.getValue());					
					target.subtractBalance(card.getValue());	
				}		
			}
			
			else if(card.getCardName().equalsIgnoreCase("Birthday")) {
				ArrayList<Player> players = gml.getPlayers();
				
				displayCard(card, "COLLECT FROM EVERYONE");
				
				gml.collectFromEveryone(10000);
				
				gui.displayText("YOU GOT " + card.getCardName() + "\n" 
						+ currentPlayer.getName() + ": +" + (card.getValue() * players.size()) + "\n"
						+ "Everyone : -" + card.getValue());
			}
			break;
		}
	}
	
	public Player choosePlayer(int situation) {
		int index = 0;
		ChoosePlayerUI chooseUI = new ChoosePlayerUI(situation);
		ChoosePlayerController chooseCont = new ChoosePlayerController (chooseUI, gml.getPlayers(), currentPlayer);
		
		boolean run = true;
		
		do {
			System.out.print("");

			switch (chooseCont.getChoice()) {
			case 0: case 1: case 2:
				index = chooseCont.getChoice();
				run = false;
				break;
			case -2:
				chooseUI = new ChoosePlayerUI(situation);
				chooseCont = new ChoosePlayerController (chooseUI, gml.getPlayers(), currentPlayer);
				break;
			}
		}while (run);
		
		return gml.getPlayers().get(index);
	}
	
	public void displayCard(ActionCard c, String instructions) {
		ActionCardUI tempUI = new ActionCardUI();
		ActionCardController tempCont = new ActionCardController (tempUI, c, instructions);
		
		do {
			System.out.print("");
		}while (!tempCont.isClosed());
		
	}
	
//BLUE SPACES
	public void takeBlueCard(){
		BlueCard card = gml.takeBlueCard();
		int amount = gml.blueCardEffect(card);

		if(card.checkPlayerCareer(currentPlayer.getJob())) {
			displayConsole("YOU GOT " + card.getCardName() + ": +" + amount);
		} 
		else {
			for(int i = 0; i < gml.getPlayers().size(); i++){
				Player p = gml.getPlayers().get(i);
				if(p.getJob() != null) {
					if(card.checkPlayerCareer(p.getJob())) {
						System.out.println("Added");
						displayConsole("YOU PAID " + p.getName() + " " + amount);
					}
				}
			}
			displayConsole("YOU GOT " + card.getCardName() + ": -" + amount);
		}
	}
//GREEN SPACES
	public void greenSpaceEffect() {
		int temp = 1 + (int)(Math.random() * 10);
		
		if (temp % 2 == 0) {
			int raise = gml.payRaise();
			switch (raise) {
			case -1:
				displayConsole("REACHED MAXIMUM NUMBER OF PAY RAISES");
				gml.payDay();
				break;
			default:
				displayConsole("SALARY IS INCREASED BY " + raise);
				break;
			}
		}
		else {
			displayConsole("PAYDAY! : +" + (currentPlayer.getJob().getSalary() - currentPlayer.getJob().getTax()));
			gml.payDay();
		}
	}
//MAGENTA SPACES
	
	public void getMarried () {
		spin = false;
		gui.displayText("YOU ARE GETTING MARRIED\nSPIN WHEEL");
		
		do {
			System.out.print("");
			if (spin) {
				if (tempWheel  % 2 == 0) {// if wheel is even
					gui.displayText("YOU ROLLED A " + tempWheel + "\n COLLECT 10000 FROM EVERYONE" );
				}
				
				else {
					gui.displayText("YOU ROLLED A " + tempWheel + " COLLECT 5000 FROM EVERYONE" );
				}
			}
			
			
		}while (!spin);
		gml.getMarried(tempWheel);
	}
	
	public int choosePath () {
		int path = 0;
		ChoosePathController cCont;
		
		if (currentPlayer.getPosition() == 0) {
			
			ChoosePathUI cUI = new ChoosePathUI();
			cCont = new ChoosePathController ("START CAREER", "START COLLEGE", cUI);
			boolean run = true;
			
			do {
				
				System.out.print("");
				
				switch (cCont.getChoice()) {
				case 1:
					path = cCont.getChoice();
					jobSearch();
					run = false;
					break;
				case 2:
					currentPlayer.getLoan(50000);
					path = cCont.getChoice();
					run = false;
					break;
				case -2:
					cUI = new ChoosePathUI();
					cCont = new ChoosePathController ("START CAREER", "START COLLEGE", cUI);
					break;
				}
				
			}while(run);
			
		}
		
		else if (currentPlayer.getPosition() == 46) {
			ChoosePathUI cUI = new ChoosePathUI();
			cCont = new ChoosePathController ("FAMILY PATH", "CAREER PATH", cUI);
			boolean run = true;
			
			do {
				
				System.out.print("");
				
				switch (cCont.getChoice()) {
				case 1: case 2:
					path = cCont.getChoice();
					run = false;
					break;
				case -2:
					cUI = new ChoosePathUI();
					cCont = new ChoosePathController ("FAMILY PATH", "CAREER PATH", cUI);
					break;
				}
				
			}while(run);
			
		}
		System.out.println(path);
		return gml.choosePath(path);
	}
	
	public void haveChild () {
		int tempChance = GameOfLife.spinWheel();
		if (currentPlayer.isMarried()) {
			if (tempChance > 6) {// have twins
				if(currentPlayer.haveBabies(2)) {
					
					displayConsole ("YOU HAVE TWINS");
					
					gml.collectFromEveryone(10000);
				}
				else {
					displayConsole ("YOU CAN'T HAVE ANYMORE BABIES");
				}
			}
			else {// have baby
				if(currentPlayer.haveBabies(1)) {
					displayConsole ("YOU HAVE A BABY");
					gml.collectFromEveryone(5000);
				}
				else {
					displayConsole ("YOU CAN'T HAVE ANYMORE BABIES");
				}
			}
		}
		else {
			displayConsole ("YOU CAN'T HAVE BABIES");
		}
	}
	
	public void buyHouse () {
		if (!currentPlayer.hasHouse()) {
			accessLoan();
			ChooseHouseUI houseUI = new ChooseHouseUI ();
			ChooseHouseController houseCont = new ChooseHouseController (houseUI, gml.getHouseCards());
			
			boolean run = true;
			int index = -1;
			
			do {
				System.out.print("");
				
				switch (houseCont.getOption()){
					case 0: case 1: case 2: case 3: case 4: case 5:
						index = houseCont.getOption();
						if (gml.buyHouse(index)) {
							run = false;
						}
						else {
							gui.displayText("NOT ENOUGH MONEY");
							houseUI = new ChooseHouseUI ();
							houseCont = new ChooseHouseController (houseUI, gml.getHouseCards());
						}
						break;
					
					case -2:
						houseUI = new ChooseHouseUI ();
						houseCont = new ChooseHouseController (houseUI, gml.getHouseCards());
						break;
				}
			}while (run);
			
			closeLoan();
		}
		else {
			displayConsole("YOU ALREADY HAVE A HOUSE");
		}
	}
	
	public void graduate () {
		if(!currentPlayer.hasDegree()) {
			gml.graduate();
			displayConsole(currentPlayer.getName() + " GRADUATED");
		}
		else
			displayConsole("YOU ALREADY HAVE A DEGREE");
	}
	
	public void collegeCareerChoice () {
		
		boolean run = true;
		int index;
		
		CareerCard c1 = gml.takeCareerCard();
		SalaryCard s1 = gml.takeSalaryCard();
		CareerCard c2 = gml.takeCareerCard();
		SalaryCard s2 = gml.takeSalaryCard();		
		
		ChooseCareerUI careerUI = new ChooseCareerUI ();
		ChooseCareerController careerCont = new ChooseCareerController (careerUI, c1, s1, c2, s2);
		
		do {
			System.out.print("");
			
			switch (careerCont.getChoice()) {
			case 1: case 2:
				index = careerCont.getChoice();
				run = false;
				
				if(index == 1) {
					displayConsole ("YOU ARE HIRED!");
					gml.setCareer (c1, s1);
					gml.returnCareerCard(c2);
					gml.returnSalaryCard(s2);
				}
				else {
					displayConsole ("YOU ARE HIRED!");
					gml.setCareer(c2, s2);
					gml.returnCareerCard(c1);
					gml.returnSalaryCard(s1);
				}
				break;
			case -1 :
				careerUI = new ChooseCareerUI ();
				careerCont = new ChooseCareerController (careerUI, c1, s1, c2, s2);
				break;
			}
			
			
		}while (run);
	}
	
	@SuppressWarnings("unused")
	public void jobSearch () {
		boolean find = false;
		CareerCard career;
		int ctr = 0;
		
		do {
			
			career = gml.takeCareerCard();
			
			if(career.getNeedDegree() && !currentPlayer.hasDegree()) {
				gml.returnCareerCard(career);
				ctr ++;
			}	
			else 
				find =true;
			
			if (ctr > 14) {
				career = null;
				find = true;
			}
			
			if (career == null) 
				break;
			
		}while(!find);
		
		if(career != null){
			int choice;
			boolean run = true;
			SalaryCard salary =	gml.takeSalaryCard();
			
			if(currentPlayer.hasCareer()) {
				
				JobSearchUI jobUI = new JobSearchUI();
				JobSearchController jobCont = new JobSearchController(jobUI, career, salary);
				do {
					System.out.print("");
					
					switch(jobCont.getChoice()) {
					case 1: 
						displayConsole("LOYALTY IS PRIORITY");
						gml.returnCareerCard(career);
						gml.returnSalaryCard(salary);
						run = false;
						break;
					case 2:
						gml.returnCareerCard();
						gml.returnSalaryCard();
						gml.jobSearch(career, salary);
						displayConsole("YOU HAVE A NEW CAREER!");
						displayCareer (career, salary);
						run = false;
						break;
					case -1:
						jobUI = new JobSearchUI();
						jobCont = new JobSearchController(jobUI, career, salary);
						break;
					}
					
				}while (run);
			}
			
			else {
				gml.jobSearch(career, salary);
				displayConsole("YOU ARE HIRED!");
				displayCareer (career, salary);
			}
		}
		else
			displayConsole ("NO ACTIVE JOBS FOUND");		
	}
	
	public void displayCareer (CareerCard c, SalaryCard s) {
		CareerUI careerUI = new CareerUI ();
		CareerController careerCont = new CareerController (careerUI, c, s);
		
		do {
			System.out.print("");		
		}while (!careerCont.isClosed());
	}
	
// BOARD GUI DRAW
	public void setStartDraw () {
		int numPlayers = gml.getPlayers().size();

		for(int i = 1; i <= numPlayers; i++) {
			movePlayer(i, 0);
		}
	}
	
	public void movePlayer (int player, int position){
		SpaceController space = board.getPlayerSpacePosition(player, position);
		int x = space.getX();
		int y = space.getY();
		gui.movePlayer(player, x, y);
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
			if (access && currentPlayer.getBalance() >= 25000) {
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
			if (finish) {
				if(currentPlayer.getBalance() >= 0 || currentPlayer.isFinish())
					turn = false;
				else {
					ExecutorService exec = Executors.newSingleThreadExecutor();
					Future<?> task = null;
					
					if (task != null)
						task.cancel(true);
					
					task = exec.submit(new Runnable (){
						@Override
						public void run() {
							displayConsole ("GET LOAN FIRST!");
						}
					});
				}
			}
			break;
		}
	}
	
	public boolean isOver() {
		return over;
	}
 
//GUI METHODS
    
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

