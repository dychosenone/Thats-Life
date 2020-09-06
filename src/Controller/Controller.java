package Controller;
import java.awt.event.*;
import Model.GameOfLife;

import View.GUI;

public class Controller implements ActionListener{
	
	GameOfLife gml;
	GUI gui;
	
	public Controller (GUI gui, GameOfLife gml) {
		this.gui = gui;
		this.gml = gml;
		
		gui.setListener(this);
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
}

