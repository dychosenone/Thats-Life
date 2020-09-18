package Model.BlueCard;

public class BlueCard {

    private String cardName;
    private int cardType;
    private boolean cardOpen;
    private String career;

    public BlueCard (String cardName, int cardType, String career){

        this.cardName = cardName;
        this.cardType = cardType;
        this.cardOpen = true;
        this.career = career;

    }

}
