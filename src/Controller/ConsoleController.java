package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import View.ConsoleUI;

public class ConsoleController implements WindowListener{
	
	int option = 0;
	ConsoleUI gui;
	
	public ConsoleController (ConsoleUI ui, String message) {
		
		System.out.println("CONSOLE controller");
		
		gui = ui;
		gui.setWindowListener(this);
		
		gui.displayMessage(message);
	}
	
	public boolean isClosed () {
		return option == 1;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		option = 1;
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
