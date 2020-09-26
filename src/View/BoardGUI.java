package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class BoardGUI extends JPanel {

    private BufferedImage boardPhoto;

    private int x1, y1;
    private int x2, y2;
    private int x3, y3;

    private final int radius = 7;

    public BoardGUI() {

    	x1 = 0;
    	x2 = 0;
    	x3 = 0;

    	y1 = 0;
    	y2 = 0;
    	y3 = 0;

    	try {
    	this.boardPhoto = ImageIO.read(new File("../Thats-Life/src/Config/board-01.jpg"));
    	
    	boardPhoto.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
    	
    	} catch (Exception e) {
    		System.out.println("An Error Occurred.");
    		e.printStackTrace();
    	}
    }

    public void drawPlayers (Graphics g) {

		for (int i = 1; i <= 3; i++) {

			if (i == 1) {
				g.setColor(new Color (255, 226, 33));
				g.fillOval(x1, y1, radius, radius);
			} else if (i == 2) {
				g.setColor(new Color (207, 255, 172));
				g.fillOval(x2, y2, radius, radius);
			} else if (i == 3) {
				g.setColor(new Color (113, 179, 255));
				g.fillOval(x3, y3, radius, radius);
			}
		}
	}
	public void setPlayer (int player, int x, int y){

			switch (player) {
				case 1:
					this.x1 = x - (radius / 2);
					this.y1 = y - (radius / 2);
					break;
				case 2:
					this.x2 = x - (radius / 2);
					this.y2 = y - (radius / 2);
					break;
				case 3:
					this.x3 = x - (radius / 2);
					this.y3 = y - (radius / 2);
					break;
			}
			revalidate();
			repaint();
		}

	@Override
    protected void paintComponent (Graphics g) {
    	 super.paintComponent(g);
    	 g.drawImage(this.boardPhoto, 0, 0, this.getWidth(), this.getHeight(), this);
    	 drawPlayers(g);
    }

}