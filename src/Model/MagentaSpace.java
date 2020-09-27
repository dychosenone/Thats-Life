package Model;

public class MagentaSpace extends Space {
	
	private String magentaType;

	/**
	 * The magenta space extends the Space class. The magenta space has a magentaType which is the type of magentaSpace.
	 * @param number board position number
	 * @param spaceName the name of space
	 * @param isChoosePath if has choose path
	 * @param pathJumpString the pathJump if any (use -1 if none)
	 * @param magentaType the type of magenta
	 */
	public MagentaSpace (int number, String spaceName, boolean isChoosePath, int pathJumpString, String magentaType) {
		super (number, spaceName, isChoosePath, pathJumpString);
		this.magentaType = magentaType;

	}

	/**
	 * Gets the type of magenta space and returns it.
	 * @return Type of Magenta Space
	 */
	public String getMagentaType () {
		return this.magentaType;
	}
}
