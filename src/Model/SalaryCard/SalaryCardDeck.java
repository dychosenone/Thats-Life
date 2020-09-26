package Model.SalaryCard;
import java.util.*;

import Model.Career.CareerCard;

public class SalaryCardDeck {

    private final int NUM_CARDS = 10;
    private ArrayList<SalaryCard> cards;

    public SalaryCardDeck () {
        cards = new ArrayList<SalaryCard>();

        for(int i = 0; i < NUM_CARDS; i++){
            cards.add(new SalaryCard());
        }
        Collections.shuffle(cards);
    }
    
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
    
    public void returnCard (SalaryCard c) {
    	c.returnCard();
    }
    
    public void reshuffleCards () {
        Collections.shuffle (cards);
    }

}
