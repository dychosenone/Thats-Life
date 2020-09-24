package Model.Player;

import Model.HouseCard.HouseCard;

public class House {
	
	private String name;
	private int value;
	
	public House (HouseCard h) {
		name = h.getHouseName();
		value = h.getValue();
	}
	
	public String getName () {
		return name;
	}
	
	public int getValue () {
		return value;
	}
}
