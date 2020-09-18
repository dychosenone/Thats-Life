package Model.BlueCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import Model.BlueCard.CardTypes.*;



public class BlueCardDeck {

    private ArrayList<BlueCard> deck;
    private int numCards;
    private int counter = 0;
    
    public BlueCardDeck () {
        deck = new ArrayList<BlueCard>();
        String cardTemp[] = new String[7];
        int i = 0;

        try {
            Scanner file = new Scanner ("../src/Config/BlueCard.txt");
            
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
        	switch(cardSplit[0]) {
        	
        	case "Lawsuit":
        		deck.add(new Lawsuit());
        		break;
        	
        	}
        }

    }
    public BlueCard takeCard (){

        if(counter == numCards) {
            reShuffleCards();
        }

        BlueCard card = deck.get(counter);
        
        this.counter++;

        return card;
    }
    
    public void reShuffleCards() {
        for (int i = 0; i < this.numCards; i++) {

            deck.get(i).changeAvailability(true);
        }
        Collections.shuffle(deck);
    }

}
