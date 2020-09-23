package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class BoardGUI extends JPanel {

    private BufferedImage boardPhoto;
    private final int radius = 6;
    
    
    public BoardGUI() {

    	
    	try {
    	this.boardPhoto = ImageIO.read(new File("../Thats-Life/src/Config/board-01.jpg"));
    	
    	boardPhoto.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
    	
    	} catch (Exception e) {
    		System.out.println("An Error Occured.");
    		e.printStackTrace();
    	}
    }
    

    @Override
    public void paintComponent (Graphics g){
    	 super.paintComponent(g);
    	 g.drawImage(this.boardPhoto, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void showPlayer (Graphics graphic, int x, int y, int num) {
    	
    	x = x - (radius/2);
    	y = y - (radius/2);
    	
    	switch(num) {
    		case 1:
    			graphic.setColor(Color.WHITE);
    			break;
    		case 2:
    			graphic.setColor(Color.CYAN);
    			break;
    		case 3:
    			graphic.setColor(Color.BLACK);
    			break;
    	}
    	
    	graphic.fillOval(x, y, radius, radius);
    	
    	
    }
    
    

    

}