package Controller;
import java.awt.event.*;
import Model.GameOfLife;

import View.GUI;

public class Controller implements ActionListener, KeyListener{
	
	GameOfLife gml;
	GUI gui;
	
	public Controller (GUI gui, GameOfLife gml) {
		this.gui = gui;
		this.gml = gml;
		
		gui.setListener(this);
		gui.setKeyListener(this);
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "Spin Wheel":
			gml.spinWheel();
			break;
			
		case "Enter":
			gui.getInput();
			break;
		}
	}
	
    @Override
	public void keyPressed(KeyEvent e) {
    	int code = e.getKeyCode();
    	
    	if (code == 10) {
    		gui.getInput();
    	}
	}
    
    public void keyTyped (KeyEvent e) {
    	
    }
    
    public void keyReleased (KeyEvent e) {
    	
    }
}

