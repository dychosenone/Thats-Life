package Model.ActionCard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ActionCardDeck {

    private int counter = 0;
    private final int MAX_CARDS = 50;
    private final int NUM_CARD_TYPES = 4;
    private ArrayList<ActionCard> actionCards;

    private int numCards;
    private double cardTypePercent[];

    /**
     * Generates ActionCards to be placed in the Model.ActionCard.ActionCardDeck. The details of the actionCard are all listed in ../config/ActionCards.txt
     * First config parameter is the number of cards to be generated, 2nd parameter of config is the name of the card and last is the
     * type of ActionCard to be generated.
     * 1 - Get from the Bank
     * 2 - Pay the Bank
     * 3 - Get from Model.Player.Players
     * 4 - Pay the Model.Player.Players
     */
    public ActionCardDeck () {
        actionCards = new ArrayList<ActionCard>();

        try {
            Scanner file = new Scanner(new File("../Thats-Life/src/Config/ActionCard.txt"));
            String firstLine = file.nextLine();
            String configSplit[] = firstLine.split(" ");

            String cards[] =new String[50];

            int cardTypeNumber[] = new int[4];

            this.numCards = Integer.parseInt(configSplit[0]);
            this.cardTypePercent = new double[4];


            for(int i = 0; i < NUM_CARD_TYPES; i++ ){
                cardTypePercent[i] = Double.parseDouble(configSplit[i+1]);
            }
            int j = 0;
            for(int i = 5; i < NUM_CARD_TYPES; i++){
                cardTypeNumber[j] = Integer.parseInt(configSplit[i]);
                j++;
            }

            int i = 0;
            while (file.hasNextLine()) {
                cards[i] = file.nextLine();
                i++;
            }
            int k = 0;
            for(i = 0; i < NUM_CARD_TYPES; i++){
                for(j = 0; j < cardTypePercent[i] * numCards; j++){


                    switch(i){
                        case 0:
                            if(k == cardTypeNumber[i]){
                                k = 0;
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");

                            }
                            else {
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;
                            }
                            break;
                        case 1:
                            if(k == cardTypeNumber[i]){
                                k = 0 + cardTypeNumber[0];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");

                            }
                            else if(k == 0){
                                k = 0 + cardTypeNumber[i];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;

                            }
                            else {
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;
                            }
                            break;
                        case 2:
                            if(k == cardTypeNumber[i]){
                                k = 0 + cardTypeNumber[0] + cardTypeNumber[1];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");

                            }
                            else if(k == 0){
                                k += cardTypeNumber[0] + cardTypeNumber[1];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;

                            }
                            else {
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;
                            }
                            break;
                        case 3:
                            if(k == cardTypeNumber[i]){
                                k = 0 + cardTypeNumber[0] + cardTypeNumber[1] + cardTypeNumber[2];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");

                            }
                            else if(k == 0){
                                k += cardTypeNumber[0] + cardTypeNumber[1] + cardTypeNumber[2];
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;

                            }
                            else {
                                String inputSplit[] = new String[2];
                                inputSplit = cards[k].split(" ");
                                actionCards.add(new ActionCard(Integer.parseInt(inputSplit[1]), inputSplit[0]));
                                k++;
                            }
                            break;


                    }

                }
                k = 0;
            }


            file.close();
        }
        catch (Exception e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
        Collections.shuffle(actionCards);
    }

    /**
     * The function first checks if there are still any cards in the deck, if no, then it calls the reShuffleCard function to reshuffle the deck.
     * This function then takes a card from the deck. It then increments the counter by 1.
     * @return top most card
     */
    public ActionCard takeCard (){

        if(counter == 50) {
            reShuffleCards();
        }

        ActionCard card = actionCards.get(counter);
        
        this.counter++;

        return card;
    }

    /**
     * This function takes a card from the deck. This function has no increment
     * @return top most card in the deck.
     */
    public ActionCard getCard () {
    	ActionCard card = actionCards.get(counter);
    	
    	return card;
    }

    /**
     * This function returns the number of cards left available on the deck.
     * @return Number of Cards available on the deck.
     */
    public int checkCardsAvail () {

        return 50 - this.counter;

    }

    /**
     * This function has no return value. The function sets all ActionCards to available.
     * It then reshuffles all the cards.
     */
    public void reShuffleCards () {
        for (int i = 0; i < this.MAX_CARDS; i++) {

            actionCards.get(i).changeAvailability(true);
        }
        Collections.shuffle(actionCards);
    }

    /**
     * This function shows all cards in the deck.
     * Does not return any value thus a void function. For the meantime, it prints out in the console all the cards
     * in the deck.
     */
    public void showAllCards () {

        int j = actionCards.size();

        for (int i = 0; i < j; i++){

            String cardName = actionCards.get(i).getCardName();
            int cardType = actionCards.get(i).getCardType();
            int cardValue = actionCards.get(i).getValue();

            System.out.println(cardName + " " + cardType + " " + cardValue);

        }
        System.out.println("Cards Generated: " + j);
    }
}
