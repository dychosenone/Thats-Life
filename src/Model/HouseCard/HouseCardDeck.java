package Model.HouseCard;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;

public class HouseCardDeck {

	private ArrayList<HouseCard> deck;
	private final int NUM_HOUSES = 6;
	private int counter = 0;
	
	public HouseCardDeck () {
		
		deck = new ArrayList<HouseCard> ();
		String temp[] = new String[NUM_HOUSES];
		int i = 0;
		
		
		try {
			
			Scanner file = new Scanner (new File ("../Thats-Life/src/Config/HouseCard.txt"));
			
			while(file.hasNextLine()) {
				
				temp[i] = file.nextLine();
				i++;
				
				
			}
			file.close();
			
		} catch (Exception e) {
			System.out.println("An Error Occured.");
			e.printStackTrace();
		}
		
		for(i = 0; i < NUM_HOUSES; i++) {
			String tempSplit[] = temp[i].split(" ");
			System.out.println("");
			String houseName = tempSplit[0];
			int value = Integer.parseInt(tempSplit[1]);
			
			deck.add(new HouseCard(houseName, value));
			
			
		}
		
		//Collections.shuffle(deck);
		
	}
	
	public HouseCard getCard (int option) {
		
		if(counter >= NUM_HOUSES)
			reShuffleCards();
			
		HouseCard card = deck.get(option);
		this.deck.get(option).setAvailable(false);
		counter++;
		return card;
	}
	
	public ArrayList <HouseCard> getHouseCards (){
		return deck;
	}
    public int checkCardsAvail () {

        return (NUM_HOUSES - this.counter - 1);

    }

	
    public void reShuffleCards () {
        for (int i = 0; i < this.NUM_HOUSES; i++) {

            deck.get(i).setAvailable(true);
        }
        Collections.shuffle(deck);
    }

	
}
