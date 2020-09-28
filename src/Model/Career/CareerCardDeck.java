package Model.Career;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.Player.Career;

public class CareerCardDeck {

    private final int NUM_CAREER = 7;
    private ArrayList <CareerCard> careerCard;

    /**
     * This constructor gets the career card text config file and generates career cards to be placed in an ArrayList of
     * Career Cards. The Career cards are then shuffled.
     */
    public CareerCardDeck () {

        careerCard = new ArrayList<CareerCard>();
        String input[] = new String[NUM_CAREER];
        int i = 0;
        
        try {
            Scanner file = new Scanner(new File ("./Config/CareerCards.txt"));
            
            while (file.hasNextLine()) {
                input[i] = file.nextLine();
                i++;	

            }
            file.close();
        }
        catch (Exception e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
        
        for(i = 0; i < NUM_CAREER; i++){

        	String split[] = input[i].split(" ");
            
            String cardName = split[0];
            int min = Integer.parseInt(split[1]);
            int max = Integer.parseInt(split[2]);
            boolean college = Boolean.parseBoolean(split[3]);
            
            careerCard.add(new CareerCard(cardName, min, max, college));
        }
        
        Collections.shuffle(careerCard);

    }

    /**
     * This function takes a career card and returns the CareerCard. When a careerCard is taken, the cards are looped and
     * it is checked if the career Card is available before returning the card.
     * @return CareerCard
     */
    
    public CareerCard takeCard (){
    	reshuffleCards ();
    	CareerCard card;

    	for (int index = 0; index < careerCard.size(); index ++) {
    		if (careerCard.get(index).getAvailability()) {
    			careerCard.get(index).takeCard();
    			card = careerCard.get(index);
    			return card;
    		}
    	}
    	
    	return null;
    }

    /**
     * Function returns the career card c.
     * @param c Career Card
     */
    public void returnCard (CareerCard c) {
    	careerCard.get(careerCard.indexOf(c)).returnCard();
    }

    /**
     * This function finds a careerCard given the Career C. It then returns the CareerCard when it finds a match, if not
     * it returns null.
     * @param c Career class
     * @return CareerCard
     */
    public CareerCard findCard(Career c) {
    	int i;
    	
    	for (i = 0; i <careerCard.size(); i++) {
    		if(careerCard.get(i).getCareerName().equalsIgnoreCase(c.getPosition())) {
    			return careerCard.get(i);
    		}
    	}
    	
    	return null;
    }

    /**
     * This function reshuffles the deck of Career Cards.
     */
    public void reshuffleCards () {
        Collections.shuffle (careerCard);
    }

    /**
     * This function prints out all of the careerCards in the console.
     */
    public void printCards () {
    	int i;
    	for (i = 0; i < careerCard.size(); i++) {
    		System.out.println(careerCard.get(i).toString());
    	}
    }

}
