package Model;

public class 	Space {
	
	private int spaceNumber;
	private String spaceName;
	private boolean isChoosePath;
	private int pathJump;
	
	
	
	public Space (int number, String spaceName, boolean isChoosePath, int pathJump) {
		this.spaceNumber = number;
		this.spaceName = spaceName;
		this.isChoosePath = isChoosePath;
		this.pathJump = pathJump;
		
	}
	
	public String getName () {
		return this.spaceName;
	}
	public int getSpaceNumber () {
		return this.spaceNumber;
	}
	
	public boolean isChoosePath () {
		return this.isChoosePath;
	}
	public int pathJump () {
		return this.pathJump;
	}
}
