package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.ActionCard.ActionCard;
import View.ActionCardUI;
import View.ConsoleUI;

public class ActionCardController implements WindowListener{
		
		int option = 0;
		ActionCardUI gui;
		
		public ActionCardController (ActionCardUI ui, ActionCard c, String instructions) {
			
			System.out.println("CONSOLE controller");
			
			gui = ui;
			gui.setWindowListener(this);
			
			gui.displayCard(c, instructions);
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
