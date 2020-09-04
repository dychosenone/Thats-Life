package Model.SalaryCard;
import java.util.*;

public class SalaryCardDeck {

    private final int NUM_CARDS = 10;
    private int counter = 0;
    private ArrayList<SalaryCard> cards;

    public SalaryCardDeck () {
        cards = new ArrayList<SalaryCard>();

        for(int i = 0; i < NUM_CARDS; i++){
            cards.add(new SalaryCard());
        }
        Collections.shuffle(cards);
    }

    public SalaryCard takeCard () {

        SalaryCard tempCard = cards.get(counter);
        counter++;
        return tempCard;

    }
    public void reshuffleCards () {
        Collections.shuffle (cards);
    }

}
