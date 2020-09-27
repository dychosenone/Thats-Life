package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;
import View.JobSearchUI;

public class JobSearchController implements ActionListener, WindowListener{
private int choice = 0;
	
	private JobSearchUI ui;
	
	public JobSearchController (JobSearchUI gui, CareerCard c, SalaryCard s) {
		
		ui = gui;
		
		ui.setActionListener(this);
		ui.setWindowListener(this);
		
		ui.changeOrStay(c, s);
	}
		
	public int getChoice () {
		return choice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "STAY":
			choice = 1;
			ui.dispose();
			break;
		case "CHANGE":
			choice = 2;
			ui.dispose();
			break;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		choice = -1;
		
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
