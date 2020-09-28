package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;


public class BoardController {

	
	private ArrayList<SpaceController> playerOne;
	private ArrayList<SpaceController> playerTwo;
	private ArrayList<SpaceController> playerThree;
	
	private final int NUM_SPACES = 134;

	public BoardController () {
		
		playerOne = new ArrayList<SpaceController>();
		playerTwo = new ArrayList<SpaceController>();
		playerThree = new ArrayList<SpaceController>();
		
		String tempOne[] = new String[NUM_SPACES];
		String tempTwo[] = new String[NUM_SPACES];;
		String tempThree[] = new String[NUM_SPACES];
		
		try {
			Scanner fileOne = new Scanner (new File("./src/Config/PlayerOne.txt"));
			Scanner fileTwo = new Scanner (new File("./src/Config/PlayerTwo.txt"));
			Scanner fileThree = new Scanner (new File("./src/Config/PlayerThree.txt"));
			
			for(int i = 0; i < NUM_SPACES; i++) {
				
				tempOne[i] = fileOne.nextLine();
				tempTwo[i] = fileTwo.nextLine();
				tempThree[i] = fileThree.nextLine();
				
			}
			
			fileOne.close();
			fileTwo.close();
			fileThree.close();
			
			
			
		} catch (Exception e) {
			System.out.println("An Error Occured.");
			e.printStackTrace();
		}
		
		for(int i = 0; i < NUM_SPACES; i++) {
			
			String tempOneSplit[] = tempOne[i].split(" ");
			String tempTwoSplit[] = tempTwo[i].split(" ");
			String tempThreeSplit[] = tempThree[i].split(" ");
			
			playerOne.add(new SpaceController(Integer.parseInt(tempOneSplit[0]), Integer.parseInt(tempOneSplit[1]), i));
			playerTwo.add(new SpaceController(Integer.parseInt(tempTwoSplit[0]), Integer.parseInt(tempTwoSplit[1]), i));
			playerThree.add(new SpaceController(Integer.parseInt(tempThreeSplit[0]), Integer.parseInt(tempThreeSplit[1]), i));
			
		}
	}
	
	public SpaceController getPlayerSpacePosition (int player, int position) {
		
		if(player == 1)
			return playerOne.get(position);
		else if (player == 2)
			return playerTwo.get(position);
		else if (player == 3)
			return playerThree.get(position);
		else
			return null;
	}
	
	
}
