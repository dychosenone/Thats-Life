package Model.Player;

import Model.HouseCard.HouseCard;

public class House {
	
	private String name;
	private int value;

	/**
	 * Takes the house card and gets the HouseCard name and value. Sets the values to this class' HouseName and Value
	 * @param h HouseCard
	 */
	public House (HouseCard h) {
		name = h.getHouseName();
		value = h.getValue();
	}

	/**
	 * Returns name of House
	 * @return House Name
	 */
	public String getName () {
		return name;
	}

	/**
	 * Returns value of House
	 * @return House Value
	 */
	public int getValue () {
		return value;
	}
}
