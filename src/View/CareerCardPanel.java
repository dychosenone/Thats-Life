package View;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.ActionCard.ActionCard;
import Model.Career.CareerCard;


public class CareerCardPanel extends JPanel{
	
	private JLabel lblCareerName;
	private JLabel lblCareerRaises;
	
	public CareerCardPanel() {
		
		setBackground(new Color (86,117,114));
		setLayout(null);		
		
		init();

		setSize (220, 237);
	}
	
	public void init() {
		
		JLabel lblTitle = new JLabel("Career Card");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 200, 26);
		add(lblTitle);
		
		setVisible (true);
		lblCareerName = new JLabel();
		lblCareerName.setForeground(Color.WHITE);
		lblCareerName.setFont(new Font("Quicksand Light", Font.BOLD, 24));
		lblCareerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCareerName.setBounds(10, 78, 200, 33);
		
		lblCareerRaises= new JLabel();
		lblCareerRaises.setHorizontalAlignment(SwingConstants.CENTER);
		lblCareerRaises.setForeground(Color.WHITE);
		lblCareerRaises.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
		lblCareerRaises.setBounds(10, 149, 200, 33);
	}
	
	public void displayCareerCard (CareerCard c) {		
		lblCareerName.setText(c.getCareerName());
		lblCareerRaises.setText(Integer.toString(c.getPayRaise()));
		
		add(lblCareerName);
		add(lblCareerRaises);
	}
}
