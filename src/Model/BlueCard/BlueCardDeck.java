package Model.BlueCard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlueCardDeck {

    private ArrayList<BlueCard> deck;
    private int numCards;
    private int counter = 0;

    /**
     * Takes the blueCard config file and generates BlueCards based on the configuration settings.
     * Generates blueCards and stores it in a BlueCard ArrayList.
     */
    public BlueCardDeck () {
        deck = new ArrayList<BlueCard>();
        String cardTemp[] = new String[7];
        int i = 0;

        try {
            Scanner file = new Scanner (new File ("./Config/BlueCard.txt"));
            
            this.numCards = Integer.parseInt(file.nextLine());

            while(file.hasNextLine()){
                cardTemp[i] = file.nextLine();
                i++;
            }
            file.close();

        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        for(i = 0; i < numCards; i++) {
        	String cardSplit[] = cardTemp[i].split(" ");
        	String cardName = cardSplit[0];
        	String career = cardSplit[1];
        	deck.add(new BlueCard(cardName, career));
        	
        }
        Collections.shuffle(deck);

    }

    /**
     * This function takes a blue card from the deck and returns it to the player. The function checks to make sure
     * that there are still available cards. If there are no available cards, the reshuffle function is called.
     * @return Blue Card
     */
    public BlueCard takeCard (){

        if(counter == numCards) {
            reShuffleCards();
        }

        BlueCard card = deck.get(counter);
        
        this.counter++;

        return card;
    }

    /**
     * This function reshuffles the Blue Card deck.
     */
    public void reShuffleCards() {
        Collections.shuffle(deck);
        counter = 0;
    }

}
