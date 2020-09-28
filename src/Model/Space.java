package Model;

public class Space {
	
		private int spaceNumber;
		private String spaceName;
		private boolean isChoosePath;
		private int pathJump;


	/**
	 * Initializes the Space using the Space constructor. The constructor sets the board Position number of the space, the type of space,
	 * if there is a choosePath, and the pathJump of the space.
	 * @param number number of the Space (Position)
	 * @param spaceName Type of Space
	 * @param isChoosePath If there is a choose path
	 * @param pathJump pathJump (if any) if none use -1
	 */
	public Space (int number, String spaceName, boolean isChoosePath, int pathJump) {
		this.spaceNumber = number;
		this.spaceName = spaceName;
		this.isChoosePath = isChoosePath;
		this.pathJump = pathJump;
		
	}

	/**
	 * Gets the name of the space. (Orange, Green, Blue, etc..)
	 * @return Name of Space
	 */
	public String getName () {
		return this.spaceName;
	}

	/**
	 * Gets the Space Number, or the position in the board.
	 * @return Space Number / Board Position
	 */
	public int getSpaceNumber () {
		return this.spaceNumber;
	}

	/**
	 * Takes the space Jump of the board, if no space Jump, value is -1.
	 * @return pathJump of Space (if any) returns -1 none.
	 */
	public int pathJump () {
		return this.pathJump;
	}
}
