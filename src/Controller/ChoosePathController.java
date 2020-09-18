package Controller;
import java.awt.event.*;

import View.ChoosePath;

public class ChoosePathController implements ActionListener {
	
	private int choice = 0;
	private String path1;
	private String path2;
	
	private ChoosePath ui;

	public ChoosePathController (String path1, String path2, ChoosePath ui) {
		
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
		ui.dispose();
		ui.setVisible(false);
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
}
