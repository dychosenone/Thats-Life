package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import Model.GameOfLife;
import View.GUI;
import View.MainMenu;

public class MainMenuController implements ActionListener{
	
	private ExecutorService e = Executors.newSingleThreadExecutor();
	private Future<?> task;
	
	private MainMenu ui;
	private boolean run = true;
	
	public MainMenuController (MainMenu gui) {
		
		this.ui = gui;
		
		ui.setActionListener(this);
		
	}
	
	public void runGame () {
		
		if (task != null)
			task.cancel(true);
		
		task = e.submit(new Runnable (){
			@Override
			public void run() {
				startGame();
			}
		});
	}
	
	public boolean getRun () {
		return run;
	}
	
	public void startGame () {
		boolean run = true;
		
		while (run) {
			ui.dispose();
			GameOfLife game = new GameOfLife ();	
			GUI gui = new GUI();
				
			Controller cont = new Controller (gui, game);
			System.out.println("MAIN GAME START");
			cont.startGame();
			
			if(cont.isOver()) {
				run = false;
				ui = new MainMenu ();
				MainMenuController main = new MainMenuController(ui);
			}			
		}
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		switch (e.getActionCommand()) {
		case "START GAME":
			runGame();
			break;
		case "EXIT":
			System.exit(0);
			break;
		}
	}
}
