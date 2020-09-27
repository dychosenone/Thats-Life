package Model;

public class MagentaSpace extends Space {
	
	private String magentaType;
	
	public MagentaSpace (int number, String spaceName, boolean isChoosePath, int pathJumpString, String magentaType) {
		super (number, spaceName, isChoosePath, pathJumpString);
		this.magentaType = magentaType;

	}

	public String getMagentaType () {
		return this.magentaType;
	}
}
