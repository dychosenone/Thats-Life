package Model.BlueCard;

import Model.GameOfLife;
import Model.Player.Career;
import Model.Player.Player;

public class BlueCard {

    private String cardName;
    private String career;

	/**
	 * This sets the cardName and the given career set to the Blue Card.
	 * @param cardName Name of the Card
	 * @param career Career related to the Card
	 */
	public BlueCard (String cardName, String career){

        this.cardName = cardName;
        this.career = career;

    }

	/**
	 * Checks the player career if its the same with the career of the BlueCard.
	 * @param career Career of Player
	 * @return a true or false value. True if the same as Card career, otherwise return false.
	 */
	public boolean checkPlayerCareer (Career career) {
    	return career.getPosition().equalsIgnoreCase(this.career);
    }

	/**
	 * This function dictates the different cardActions it will take depending on the CardName.
	 * @param p Current Player
	 * @param career Career of current Player
	 * @param numPlayers Total number of Players playing
	 * @return the value to be added or subtracted
	 */
	public int cardAction(Player p, Career career, int numPlayers) {
    	int cardAmount = 0;
    	
    	if(checkPlayerCareer(career) == true) {
    		return 15000;
    	} else {
    	
	    	switch(cardName) {
	    		case "Lawsuit":
	    			do {
	    				cardAmount = (int) (Math.random() * (10 - 1 + 1) + 1) * 10000;
	    			} while (cardAmount > 150000 || cardAmount < 50000);

	    		case "SalaryTaxDue":
	    			Career job = p.getJob();
	    			cardAmount = job.getTax();
	    		case "TipTheServer":
	    			cardAmount = GameOfLife.spinWheel() * 1000;
	    		case "SkiAccident":
	    			cardAmount = 10000;
	    		case "ComputerRepair":
	    			int number = GameOfLife.spinWheel();
	    			
	    			if(number % 2 == 0)
	    				cardAmount = 5000;
	    			else cardAmount = 10000;
	    		case "WorldCup":
	    			cardAmount = numPlayers * 5000;
	    		case "F1Race":
	    			int value = (int) (career.getSalary() * 0.1);
	    			cardAmount = value;
	    	}
    	}
    	return cardAmount;
    
    }

	/**
	 * This function returns the name of the BlueCard.
	 * @return BlueCard name
	 */
	public String getCardName () {
    	return cardName;
    }
    
    /*
    public void changeAvailability (Boolean b) {
    	this.cardOpen = b;
    }*/
}
