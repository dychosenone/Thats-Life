package Model.BlueCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        	String cardName = cardSplit[0];
        	String career = cardSplit[1];
        	deck.add(new BlueCard(cardName, career));
        	
        }
        Collections.shuffle(deck);

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
