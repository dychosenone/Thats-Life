package Controller;

public class SpaceController {

	private int spaceNumber;
	private int x;
	private int y;
	
	public SpaceController (int x, int y, int spaceNumber) {
		
		this.x = x;
		this.y = y;
		this.spaceNumber = spaceNumber;
		
	}
	
	public int getX () {
		return this.x;
	}
	public int getY () {
		return this.y;
	}
	public int getSpaceNumber () {
		return this.spaceNumber;
	}
	
	
}
