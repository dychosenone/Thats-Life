package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	private final int SPACES = 128;
	private ArrayList <Space> spaces;
	
	public Board () {
		spaces = new ArrayList <Space>();
		
		String input[] = new String[SPACES];
		int i = 0;
		
		try {
			Scanner file = new Scanner (new File("../Thats-Life/src/Config/board.txt"));
			
			while(file.hasNextLine()) {
				
				input[i] = file.nextLine();
				i++;
				
			}
			file.close();
			
		} catch (Exception e) {
			System.out.println("An Error Occured.");
			e.printStackTrace();
		}
		
		
		for (i = 0; i < SPACES; i++) {
			
			String split[] = input[i].split(" ");
			int boardNumber = Integer.parseInt(split[0]);
			String cardName = split[1];
			boolean hasJump = Boolean.parseBoolean(split[2]);
			int cardJumpSpace = Integer.parseInt(split[3]);
					
					
			switch(split[0]){
				case "Magenta":
		
					switch(boardNumber) {
						case 13:
						case 48:
						case 59:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "getMarried"));
							break;
						case 16:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "careerChoice"));
							break;
						case 26:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "jobSearch"));
							break;
						case 46:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "choosePath"));
							break;
						case 52:
						case 95:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "buyHouse"));
							break;
						case 62:
							spaces.add(new MagentaSpace(boardNumber, cardName, hasJump, cardJumpSpace, "haveChild"));
							break;
					}
				
					break;
				case "Blue":
				case "Green":
				case "Orange":
					spaces.add(new Space(boardNumber, cardName, hasJump, cardJumpSpace));
					break;
			
			}

		}
	}
	
	public Space getSpace (int index) {
		return spaces.get(index-1);
	}
}