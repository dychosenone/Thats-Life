package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import Model.HouseCard.HouseCard;
import View.ChooseHouseUI;

public class ChooseHouseController implements ActionListener, WindowListener{
	
	private ChooseHouseUI ui;
	
	private int option = -1;
	
	private ArrayList<HouseCard> houses;
	
	public ChooseHouseController (ChooseHouseUI ui, ArrayList<HouseCard> houses) {
		this.ui = ui;
		this.houses = houses;
		
		ui.chooseHouse(houses);
		
		ui.setActionListeners(this);
		ui.setWindowListeners(this);
	}
	
	public int getOption () {
		return option;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		if(e.getActionCommand().equalsIgnoreCase(houses.get(0).getHouseName())) {
			option = 0;
			ui.dispose();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase(houses.get(1).getHouseName())) {
			option = 1;
			ui.dispose();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase(houses.get(2).getHouseName())) {
			option = 2;
			ui.dispose();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase(houses.get(3).getHouseName())) {
			option = 3;
			ui.dispose();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase(houses.get(4).getHouseName())) {
			option = 4;
			ui.dispose();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase(houses.get(5).getHouseName())) {
			option = 5;
			ui.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		option = -2;
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
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
