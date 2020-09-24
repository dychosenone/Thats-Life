package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PlayerPiece {
	
	private int playerNum;
	private int x;
	private int y;
	private static int radius = 6;
	
	public PlayerPiece (int playerNum) {
		
		this.x = 0;
		this.y = 0;
		
		this.playerNum = playerNum;
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
    	

	}
	
	public void setX (int x) {
		this.x  = x;
	}
	public void setY (int y) {
		this.y = y;
	}
	
}
