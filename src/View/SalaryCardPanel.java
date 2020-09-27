package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Model.SalaryCard.SalaryCard;

public class SalaryCardPanel extends JPanel{
	
	private JLabel lblSalary;
	private JLabel lblTax;
	
	public SalaryCardPanel() {
		
		setBackground(new Color (150,79,76));
		setLayout(null);		
		
		init();

		setSize (220, 237);
		
		JLabel lblTitle = new JLabel("Salary Card");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 200, 26);
		add(lblTitle);
		setVisible (true);
	}
	
	public void init() {
		lblSalary = new JLabel();
		lblSalary.setForeground(Color.WHITE);
		lblSalary.setFont(new Font("Quicksand Light", Font.BOLD, 24));
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalary.setBounds(10, 78, 200, 33);
		add(lblSalary);
		
		lblTax= new JLabel ();
		lblTax.setHorizontalAlignment(SwingConstants.CENTER);
		lblTax.setForeground(Color.WHITE);
		lblTax.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
		lblTax.setBounds(10, 149, 200, 33);
		add(lblTax);
	}
	
	public void displaySalary (SalaryCard s) {
		lblSalary.setText(Integer.toString(s.getSalary()));
		lblTax.setText("TAX : " + s.getTaxDue());
		
		add (lblSalary);
		add (lblTax);
	}
	
	
}
