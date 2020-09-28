package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	private final int SPACES = 134;
	private ArrayList <Space> spaces;


	/**
	 * When the board is created, an ArrayList of spaces is created to store all the spaces of the board.
	 * A board config file is then used to generate all the tiles and their details.
	 * For the magentaTypes, a switch statement is used to determine what type of magenta tile it is.
	 *
	 */
	public Board () {
		spaces = new ArrayList <Space>();
		
		String input[] = new String[SPACES];
		int i = 0;
		
		try {
			Scanner file = new Scanner (new File("../Config/board.txt"));
			
			while(file.hasNextLine()) {
				input[i] = file.nextLine();
				i++;
				
			}
			file.close();
			
		} catch (Exception e) {
			System.out.println("An Error Occurred.");
			e.printStackTrace();
		}

		for (i = 0; i < SPACES; i++) {
			
			String split[] = input[i].split(" ");
			int boardNumber = Integer.parseInt(split[0]);
			String cardName = split[1];
			boolean hasJump = Boolean.parseBoolean(split[2]);
			int cardJumpSpace = Integer.parseInt(split[3]);
				
			System.out.println(boardNumber);
					
			switch(split[1]){
				case "Magenta":
		
					switch(boardNumber) {
						case 9:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "graduation"));
							break;
						case 11:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "collegeCareerChoice"));
							break;
						case 14: case 48:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "getMarried"));
							break;
						case 59:
						case 26:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "jobSearch"));
							break;
						case 0:
						case 46:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "choosePath"));
							break;
						case 52: case 95: case 110:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "buyHouse"));
							break;
						case 42: case 54: case 90:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "haveChild"));
							break;
					}
				
					break;
				case "Blue":
				case "Green":
				case "Orange":
					spaces.add(new Space(boardNumber, cardName, hasJump, cardJumpSpace));
					break;
				case "End":
					spaces.add(new Space(boardNumber, cardName, hasJump, cardJumpSpace));
			
			}

		}
	}

	/**
	 * Returns the space given a position. The position is used to search of the space in the ArrayList.
	 * @param index Space position
	 * @return Space class
	 */
	public Space getSpace (int index) {
		return spaces.get(index);
	}


	/**
	 * Prints all the spaces (Space Position and type) to the console.
	 */
	public void printSpaces () {
		int i;
		
		for (i = 0; i < spaces.size(); i++) {
			if (spaces.get(i).getName().equalsIgnoreCase("Magenta")) {
				MagentaSpace temp = (MagentaSpace) spaces.get(i);
				System.out.println(temp.getSpaceNumber() + " " +temp.getMagentaType());
			}
			else {
				System.out.println(spaces.get(i).getSpaceNumber() + " " + spaces.get(i).getName());
			}
		}
	}

	/**
	 * Checks if the tile is that the last space.
	 * @param position position of player
	 * @return true if end, false if not end.
	 */
	public boolean isEnd(int position) {
		if (position == 0) {
			return false;
		}		
		return getSpace(position).getSpaceNumber() == SPACES-1;
	}
}