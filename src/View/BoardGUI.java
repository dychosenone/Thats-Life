package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class BoardGUI extends JPanel {

    private BufferedImage boardPhoto;
    private final int radius = 7;
    private ArrayList<PlayerPiece> players;

    public BoardGUI() {
		players = new ArrayList<PlayerPiece>();
		for(int i = 0; i < 3; i++){
			players.add(new PlayerPiece(i+1,300,300));
		}

    	try {
    	this.boardPhoto = ImageIO.read(new File("../Thats-Life/src/Config/board-01.jpg"));
    	
    	boardPhoto.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
    	
    	} catch (Exception e) {
    		System.out.println("An Error Occurred.");
    		e.printStackTrace();
    	}
    }

    public void paintComponent (Graphics g){
    	 super.paintComponent(g);
    	 g.drawImage(this.boardPhoto, 0, 0, this.getWidth(), this.getHeight(), this);
    }

	public void movePlayer (int player, int x, int y){
		PlayerPiece piece = players.get(player-1);
		piece.setX(x);
		piece.setY(y);
		piece.movePlayer(this.getGraphics());
	}

}