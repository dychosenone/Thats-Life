package Model.Career;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.ActionCard.ActionCard;

public class CareerCardDeck {

    private final int NUM_CAREER = 7;
    private ArrayList <CareerCard> careerCard;
    private int counter = 1;

    public CareerCardDeck () {

        careerCard = new ArrayList<CareerCard>();
        try {
            Scanner file = new Scanner(new File("../Thats-Life/src/Config/CareerCards.txt"));

            while (file.hasNextLine()) {
                String input = file.nextLine();

                String split[] = input.split(" ");
                int min = Integer.parseInt(split[1]);
                int max = Integer.parseInt(split[2]);
                boolean college = Boolean.parseBoolean(split[3]);

                for(int i = 0; i < 7; i++){
                    careerCard.add(new CareerCard(split[0], min, max, college));
                }

            }
            file.close();
        }
        catch (Exception e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }

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
