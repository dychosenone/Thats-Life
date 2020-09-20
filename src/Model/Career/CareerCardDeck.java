package Model.Career;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.ActionCard.ActionCard;

public class CareerCardDeck {

    private final int NUM_CAREER = 7;
    private ArrayList <CareerCard> careerCard;
    private int counter = 0;

    public CareerCardDeck () {

        careerCard = new ArrayList<CareerCard>();
        String input[] = new String[NUM_CAREER];
        int i = 0;
        
        try {
            Scanner file = new Scanner(new File ("../Thats-Life/src/Config/CareerCards.txt"));
            
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
    
    public CareerCard takeCard (){
   
        CareerCard card = careerCard.get(counter);
        reshuffleCards ();
        
        this.counter++;

        return card;
    }

    public void reshuffleCards () {
        Collections.shuffle (careerCard);
    }

}
