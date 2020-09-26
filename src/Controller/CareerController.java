package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.ActionCard.ActionCard;
import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;
import View.ActionCardUI;
import View.CareerUI;

public class CareerController implements WindowListener{
	
	int option = 0;
	CareerUI gui;
	
	public CareerController (CareerUI ui, CareerCard c, SalaryCard s) {
		
		gui = ui;
		gui.setWindowListener(this);
		
		gui.displayCareer(c, s);
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
