package Model;
import java.util.ArrayList;

public class Board {
	
	private static final int SPACES = 84;
	private ArrayList <Space> spaces;
	
	public Board () {
		spaces = new ArrayList <>();
		
		for (int i = 0; i < SPACES; i++) {
			spaces.add(new OrangeSpace("ActionCard"));
		}
	}
	
	public Space getSpace (int index) {
		return spaces.get(index);
	}
}
