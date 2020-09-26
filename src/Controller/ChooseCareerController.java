package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;
import View.ChooseCareerUI;

public class ChooseCareerController implements ActionListener, WindowListener{
	
	private int choice = 0;
	
	private ChooseCareerUI ui;
	
	private String career1;
	private String career2;
	
	public ChooseCareerController (ChooseCareerUI gui, CareerCard c1, SalaryCard s1, CareerCard c2, SalaryCard s2) {
		
		ui = gui;
		
		ui.setActionListener(this);
		ui.setWindowListener(this);
		
		career1 = c1.getCareerName();
		career2 = c2.getCareerName();
		
		ui.chooseCareer(c1, s1, c2, s2);
	}
		
	public int getChoice () {
		return choice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase(career1)) {
			choice = 1;
			ui.dispose();
		}
		
		if (e.getActionCommand().equalsIgnoreCase(career2)) {
			choice = 2;
			ui.dispose();
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
