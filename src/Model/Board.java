package Model;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	private static final int SPACES = 84;
	private ArrayList <Space> spaces;
	
	public Board () {
		spaces = new ArrayList<Space>();


		try {
			Scanner file = new Scanner(new File("../Thats-Life/src/Config/Board.txt"));

		} catch(Exception e){
			System.out.println("An Error Occurred.");
			e.printStackTrace();
		}
		
		for (int i = 0; i < SPACES; i++) {
			boolean add = false;
			
			if(i == 3) {
				add = true;
				spaces.add(new MagentaSpace("Get Married"));
			}
			
			else if(i == 12) {
				add = true;
				spaces.add(new MagentaSpace("Job Search"));
			}
			
			if (!add)
				spaces.add(new OrangeSpace("ActionCard"));
		}
	}
	
	public Space getSpace (int index) {
		return spaces.get(index);
	}
}
