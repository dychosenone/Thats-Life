package Model.SalaryCard;
import java.util.*;

import Model.Career.CareerCard;
import Model.Player.Career;


public class SalaryCardDeck {

    private final int NUM_CARDS = 10;
    private ArrayList<SalaryCard> cards;

    /**
     * This generates 10 Salary Cards and stores in an ArrayList of Salary Cards.
     * The ArrayList is then shuffled.
     */
    public SalaryCardDeck () {
        cards = new ArrayList<SalaryCard>();

        for(int i = 0; i < NUM_CARDS; i++){
            cards.add(new SalaryCard(i));
        }
        Collections.shuffle(cards);
    }

    /**
     * This function takes a Salary Card from the deck. The deck is first checked for an available card, The take card function
     * is then called to set availability to false, then the card is returned.
     * @return Salary Card
     */
    public SalaryCard takeCard(){
    	reshuffleCards ();
    	SalaryCard card;
    	int index = 0;
    	
    	for (index = 0; index < cards.size(); index ++) {
    		if (cards.get(index).getAvailability()) {
    			cards.get(index).takeCard();
    			card = cards.get(index);
    			return card;
    		}
    	}
    	
    	return null;
    }

    /**
     * The Salary Card return function is returned.
     * @param c The Salary Card to be returned
     */
    public void returnCard (SalaryCard s) {
    	cards.get(cards.indexOf(s)).returnCard();
    }
    
    public SalaryCard findCard(Career c) {
    	int i;
    	
    	for (i = 0; i < cards.size(); i++) {
    		if(cards.get(i).getCardNumber() == c.getSalaryCardNumber ()) {
    			return cards.get(i);
    		}
    	}
    	
    	return null;
    }

    /**
     * This function reshuffles the card deck.
     */
    public void reshuffleCards () {
        Collections.shuffle (cards);
    }

}
