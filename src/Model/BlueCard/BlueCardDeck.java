package Model.BlueCard;

import java.util.ArrayList;
import java.util.Scanner;

public class BlueCardDeck {

    private ArrayList<BlueCard> deck;

    public BlueCardDeck () {
        deck = new ArrayList<BlueCard>();
        String cardTemp[] = new String[7];
        int i = 0;

        try {
            Scanner file = new Scanner ("../src/Config/BlueCard.txt");

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




    }

}
