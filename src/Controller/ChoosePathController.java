package Controller;
import java.awt.event.*;

import View.ChoosePathUI;

public class ChoosePathController implements ActionListener, WindowListener {
	
	private int choice = 0;
	private String path1;
	private String path2;
	
	private ChoosePathUI ui;

	public ChoosePathController (String path1, String path2, ChoosePathUI ui) {
		
		this.path1 = path1;
		this.path2 = path2;
		
		this.ui = ui;
		//System.out.println("ChoosePathController Created");
		ui.setListener(this);
	}
	
	public int getChoice () {
		return choice;
	}
	
	public void closeWindow () {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(path1)) {
			ui.dispose();
			choice = 1;
		}
		
		else if (e.getActionCommand().equalsIgnoreCase(path2)) {
			ui.dispose();
			choice = 2;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
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
