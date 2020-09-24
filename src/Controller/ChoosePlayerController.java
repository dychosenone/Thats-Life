package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Player.Player;
import View.ChoosePlayerUI;

public class ChoosePlayerController implements ActionListener {
	public int choice = -1;
	
	public ChoosePlayerUI ui;
	public ArrayList <Player> players;
	
	public ChoosePlayerController (ChoosePlayerUI ui, ArrayList <Player> players, Player currentPlayer) {
		this.ui = ui;
		this.players = players;
		
		ui.choosePlayer(players, currentPlayer);
		ui.setActionListeners(this);
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
		if (e.getActionCommand().equalsIgnoreCase(players.get(1).getName())) {
			choice = 1;
			ui.dispose();
		}
		if (e.getActionCommand().equalsIgnoreCase(players.get(2).getName())) {
			choice = 2;
			ui.dispose();
		}
		
	}
}
