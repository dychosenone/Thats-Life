package Model.HouseCard;

import java.lang.String;

public class HouseCard {

	private String houseName;
	private int value;
	private boolean available;

	/**
	 * THe House card sets the House Name, the Value of the House and the Availability of the Card
	 * @param name The Name of the House
	 * @param value The Value of the House
	 */

	public HouseCard (String name, int value) {
	
		this.houseName = name;
		this.value = value;
		this.available = true;
		
	}

	/**
	 * Returns the Name of the House Card
	 * @return House Card Name
	 */
	public String getHouseName () {
		return this.houseName;
	}

	/**
	 * Returns the value of the house
	 * @return House Value
	 */
	public int getValue () {
		return this.value;
	}

	/**
	 * Sets the availability to the parameter b.
	 * @param b boolean to set the available variable.
	 */
	public void setAvailable (boolean b) {
		
		this.available = b;
	}

	/**
	 * returns the boolean variable available.
	 * @return Available. True if available, false if not.
	 */
	public boolean isAvailable () {
		return available;
	}
}
