package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import View.InputDialog;

public class InputController implements KeyListener, WindowListener {
	
	private String input;
	
	private InputDialog gui;
	private int status;
	
	public InputController (InputDialog gui, String instructions) {
		status = 0;
		
		this.gui = gui;
		
		gui.showDialog(instructions);
		gui.setKeyListener(this);
		gui.setWindowListener(this);
	}
	
	public String getInput () {
		return input;
	}
	
	public boolean isDone () {
		return status == 1;
	}
	
	public int getStatus () {
		return status;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == 10) {
    		input = gui.getInput();
    		System.out.println(input);
    		status = 1;
    		gui.dispose();
    	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		status = -1;
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
