package Model.BlueCard;

import Model.GameOfLife;
import Model.Player.Career;
import Model.Player.Player;

public class BlueCard {

    private String cardName;
    private boolean cardOpen;
    private String career;

    public BlueCard (String cardName, String career){

        this.cardName = cardName;
        this.cardOpen = true;
        this.career = career;

    }
    
    
    public boolean checkPlayerCareer (Career career) {
    	return career.getPosition().equalsIgnoreCase(this.career);
    }
    
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
    
    public void changeAvailability (Boolean b) {
    	this.cardOpen = b;
    }
    
    public String getCardName () {
    	return cardName;
    }
    

}
