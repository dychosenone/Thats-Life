package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import View.ConsoleUI;

public class ConsoleController implements WindowListener{
	
	int status = 0;
	ConsoleUI gui;
	
	public ConsoleController (ConsoleUI ui, String message) {
		
		System.out.println("CONSOLE controller");
		
		gui = ui;
		gui.setWindowListener(this);
		
		gui.displayMessage(message);
	}
	
	public boolean isClosed () {
		return status == 1;
	}
	
	public int getStatus () {
		return status;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		status = 1;
		
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
