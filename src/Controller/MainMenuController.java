package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.*;

import Model.GameOfLife;
import View.GUI;
import View.MainMenu;

public class MainMenuController implements ActionListener{
	
	private MainMenu ui;
	private boolean run = true;
	
	public MainMenuController (MainMenu gui) {
		
		this.ui = gui;
		
		ui.setActionListener(this);
		
	}
	
	public void run () {
		GameOfLife game = new GameOfLife ();	
		GUI gui = new GUI();
			
		Controller cont = new Controller (gui, game);
		System.out.println("MAIN GAME START");
		cont.startGame();
	}
	
	public boolean getRun () {
		return run;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		switch (e.getActionCommand()) {
		case "START GAME":
			System.out.println("RUNNING");
			break;
		case "INSTRUCTIONS":
			break;
		case "EXIT":
			System.exit(0);
			break;
		}
	}
}
