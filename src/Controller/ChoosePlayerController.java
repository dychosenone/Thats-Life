package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import Model.Player.Player;
import View.ChoosePlayerUI;

public class ChoosePlayerController implements ActionListener, WindowListener {
	public int choice = -1;
	
	public ChoosePlayerUI ui;
	public ArrayList <Player> players;
	
	public ChoosePlayerController (ChoosePlayerUI ui, ArrayList <Player> players, Player currentPlayer) {
		this.ui = ui;
		this.players = players;
		
		ui.choosePlayer(players, currentPlayer);
		ui.setActionListeners(this);
		ui.setWindowListeners(this);
	}
	
	public int getChoice () {
		return choice;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equalsIgnoreCase(players.get(0).getName())) {
			choice = 0;
			ui.dispose();
		}
		else if (e.getActionCommand().equalsIgnoreCase(players.get(1).getName())) {
			choice = 1;
			ui.dispose();
		}
		else if (e.getActionCommand().equalsIgnoreCase(players.get(2).getName())) {
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
		choice = -2;
		
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
