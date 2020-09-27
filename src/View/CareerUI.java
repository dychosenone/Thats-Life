package View;

import java.awt.Color;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;

public class CareerUI {
	
	JDialog main;
	
	CareerCardPanel pCareer;
	SalaryCardPanel pSalary;
	
	public CareerUI () {
		
		main = new JDialog ();
		main.getContentPane().setLayout(null);
		main.setBackground(Color.WHITE);
		main.setLocationRelativeTo(null);
		
		init();
		
        main.setSize (444, 262);
        main.setResizable(false);
        main.setVisible (true);
        main.setModal(true);
	}
	
	public void init () {
		
		pCareer = new CareerCardPanel ();
		pCareer.setBounds(0, 0, 220, 237);
		pCareer.setVisible(true);
		
		main.getContentPane().add(pCareer);
		
		pSalary = new SalaryCardPanel ();
		pSalary.setBounds(221, 0, 220, 237);
		main.getContentPane().add(pSalary);
		pSalary.setVisible(true);
		
	}
	
	public void displayCareer (CareerCard c, SalaryCard s) {
		main.repaint();
		main.revalidate();
		
		pCareer.displayCareerCard(c);
		pSalary.displaySalary(s);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener (l);
	}
}
