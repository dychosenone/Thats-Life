package Model;
import Model.Player.*;

public class MagentaSpace extends Space {
	
	private String magentaType;
	
	public MagentaSpace (int number, String spaceName, boolean isChoosePath, int pathJumpString, String magentaType) {
		super (number, spaceName, isChoosePath, pathJumpString);
		this.magentaType = magentaType;

	}
	/*
	public void changeChildTo () {
		
		int value = (int) (Math.random() * (2 - 1 + 1) + 1);
		
		if(value == 1)
			this.magentaType = "haveBaby";
		else if(value == 2)
			this.magentaType = "haveTwin";
		
	}
	*/
	public String getMagentaType () {
		return this.magentaType;
	}
}
