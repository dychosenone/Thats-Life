package View;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

public class PlayerPiece extends JPanel {

	private int playerNum;
	private int x;
	private int y;
	private static int radius = 9;

	public PlayerPiece (int playerNum, int x, int y) {

		this.x = x;
		this.y = y;
		this.playerNum = playerNum;

	}

	public void movePlayer(Graphics g){

		x = x - (radius/2);
		y = y - (radius/2);

		switch(this.playerNum) {
			case 1:
				g.setColor(new Color(52, 73, 94));
				break;
			case 2:
				g.setColor(new Color(7, 153, 146));
				break;
			case 3:
				g.setColor(new Color(12, 36, 97));
				break;
		}
		g.fillOval(x, y, radius, radius);
		repaint();
	}

	protected void paintComponent(Graphics g) {

	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
}
