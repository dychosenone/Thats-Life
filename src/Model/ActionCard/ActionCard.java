package Model.ActionCard;

public class ActionCard {

    private String cardName;
    private int value;
    private int cardType;
    private boolean cardOpen;

    /**
     * Constructor for creating a new action card. When a new ActionCard is created, the name of the card and the cardType is
     * stored in variables. The card is then set to open. A random value is then generated based on the MP specifications.
     * @param cardType is the type of Action Card
     * @param cardName name of Action Card
     */
    public ActionCard (int cardType, String cardName){
        this.cardName = cardName;
        this.cardType = cardType;

        this.cardOpen = true;

        this.value = (int) (Math.random() * (10 - 1 + 1) + 1) * 10000;

    }

    /**
     * This function changes the boolean variable cardOpen to the value b.
     * @param b is the boolean value to be set to cardOpen.
     */
    public void changeAvailability (boolean b) {
        this.cardOpen = b;
    }

    /**
     * Function Returns a boolean stating if the Card is still available or has already been picked out.
     * @return card boolean value. True = Available, False = Not Available
     */
    public boolean checkAvailability () {
        return this.cardOpen;
    }

    /**
     * This function returns the cardType when asked.
     * @return cardType
     */
    public int getCardType () {
        return this.cardType;
    }

    /**
     * This function returns the value of the card. Each card has a specific value that will be subracted or added to the player.
     * @return Card Value
     */
    public int getValue () {
        return this.value;
    }

    /**
     * This function gets and returns the card's name.
     * @return Name of ActionCard
     */
    public String getCardName () {
        return this.cardName;
    }

    /**
     * Overrides the function toString. The function returns the CardName and CardValue in string format.
     * @return ActionCard Name and ActionCard Value
     */
    @Override
    public String toString () {
        return cardName + " " + value;
    }
}
