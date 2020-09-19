package Model.BlueCard.CardTypes;

import Model.BlueCard.BlueCard;

public class Lawsuit extends BlueCard{
	
	private int amount;
	
	public Lawsuit() {
		
		super("Lawsuit", "Lawyer");
		generateAmount();
	}
	
	public int generateAmount () {
		
		int total;
		
		do{
			total = (int) (Math.random() * (10 - 1 + 1) + 1) * 10000;
		} while (total >= 50000 || total <= 150000);
		
		return total;
		
	}
		
	}
	
}