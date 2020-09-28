package Model.HouseCard;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;

public class HouseCardDeck {

	private ArrayList<HouseCard> deck;
	private final int NUM_HOUSES = 6;
	private int counter = 0;


	/**
	 * Gets houses from house text file in config. Sets the arrayList of HouseCards based on the config file.
	 */
	public HouseCardDeck () {
		
		deck = new ArrayList<HouseCard> ();
		String temp[] = new String[NUM_HOUSES];
		int i = 0;
		
		
		try {
			
			Scanner file = new Scanner (new File ("./src/Config/HouseCard.txt"));
			
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
		
	}

	/**
	 * Gets a houseCard from the deck. sets the card to false avvailability so no other player can have the same house.
	 * @param option states which house in the deck
	 * @return chosen HouseCard based on option
	 */
	public HouseCard getCard (int option) {
		
		/*
		if(counter >= NUM_HOUSES)
			reShuffleCards();
		*/
		
		HouseCard card = deck.get(option);
		this.deck.get(option).setAvailable(false);
		//counter++;
		return card;
	}

	/**
	 * Returns the entire houseCard deck
	 * @return HouseCard Deck
	 */
	public ArrayList <HouseCard> getHouseCards (){
		return deck;
	}

	/**
	 * Checks the number of houses available
	 * @return Number of Houses Available
	 */
    public int checkCardsAvail () {

        return (NUM_HOUSES - this.counter - 1);

    }

	/**
	 * Return the card to the deck. When returned, the availability is again set to true.
	 * @param index HouseCard Number
	 */
	public void returnCard (int index) {
    	deck.get(index).setAvailable(true);
    }

	/**
	 * Reshuffles the entire deck
	 */
    public void reShuffleCards () {
        for (int i = 0; i < this.NUM_HOUSES; i++) {

            deck.get(i).setAvailable(true);
        }
        Collections.shuffle(deck);
    }

	
}
