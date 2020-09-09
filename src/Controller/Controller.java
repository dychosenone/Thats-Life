package Controller;
import java.awt.event.*;
import Model.GameOfLife;

import View.GUI;

public class Controller implements ActionListener, KeyListener{
	
	private GameOfLife gml;
	private GUI gui;
	private String input;
	
	public Controller (GUI gui, GameOfLife gml) {
		this.gui = gui;
		this.gml = gml;
		
		gui.setListener(this);
		gui.setKeyListener(this);
		input = "null";
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "Spin Wheel":
			gml.spinWheel();
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

