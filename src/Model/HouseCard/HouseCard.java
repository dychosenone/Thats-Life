package Model.HouseCard;

import java.lang.String;

public class HouseCard {

	private String houseName;
	private int value;
	private boolean available;
	
	public HouseCard (String name, int value) {
	
		this.houseName = name;
		this.value = value;
		this.available = true;
		
	}
	
	public String getHouseName () {
		return this.houseName;
	}
	public int getValue () {
		return this.value;
	}
	public boolean setAvailable (boolean b) {
		
		this.available = b;
	}
}
