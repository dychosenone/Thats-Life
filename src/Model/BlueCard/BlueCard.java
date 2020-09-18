package Model.BlueCard;

import Model.Player.Career;

public abstract class BlueCard {

    private String cardName;
    private boolean cardOpen;
    private String career;

    public BlueCard (String cardName, String career){

        this.cardName = cardName;
        this.cardOpen = true;
        this.career = career;

    }
    public boolean checkPlayerCareer (Career career) {
    	
    	if(career.getPosition() == this.career) {
    		return true;
    	}
    	return false;
    }
    public void cardAction() {}
    
    public void changeAvailability (Boolean b) {
    	this.cardOpen = b;
    }
    

}
