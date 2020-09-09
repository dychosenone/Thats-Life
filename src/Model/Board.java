package Model;
import java.util.ArrayList;

public class Board {
	
	private static final int SPACES = 84;
	private ArrayList <Space> spaces;
	
	public Board () {
		spaces = new ArrayList <>();
		
		for (int i = 0; i < SPACES; i++) {
			boolean add = false;
			
			if (i == 1 || i == 20) {
				add = true;
				spaces.add(new MagentaSpace("Choose Path"));
			}
			
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
