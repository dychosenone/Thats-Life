package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;

public class JobSearchUI {
	
	private JDialog main;
	
	private JButton btnYes;
	private JButton btnNo;
	
	private JTextPane tpCareer;
	private JTextPane tpSalary;
	
	public JobSearchUI() {
		main = new JDialog ();
		main.getContentPane().setBackground(Color.WHITE);
		
    	main.getContentPane().setLayout(null);
    	main.setBackground(Color.WHITE);
    	main.setLocationRelativeTo(null);
    	
    	init();
    	
        main.setSize (526, 294);
        main.setResizable(false);
        main.setVisible (true);    
        main.setModal(true);
	}
	
	public void init () {
    	JLabel lblNewLabel = new JLabel("STAY OR CHANGE CAREER");
    	lblNewLabel.setForeground(Color.BLACK);
    	lblNewLabel.setFont(new Font("Quicksand Light", Font.PLAIN, 30));
    	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel.setBounds(10, 11, 492, 63);
    	main.getContentPane().add(lblNewLabel);
    	
    	btnYes = new JButton("STAY");
    	btnYes.setForeground(Color.WHITE);
    	btnYes.setBackground(new Color(105,102,103));
    	btnYes.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnYes.setBounds(326, 104, 147, 49);
    	main.getContentPane().add(btnYes);
    	
    	btnNo = new JButton("CHANGE");
    	btnNo.setForeground(Color.WHITE);
    	btnNo.setBackground(new Color(105,102,103));
    	btnNo.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnNo.setBounds(326, 164, 147, 49);
    	main.getContentPane().add(btnNo);
    	
    	StyledDocument doc;
		SimpleAttributeSet center = new SimpleAttributeSet();
    	
    	tpCareer = new JTextPane();
    	tpCareer.setForeground(Color.WHITE);
    	tpCareer.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpCareer.setBackground(new Color (86,117,114));
    	tpCareer.setBounds(40, 85, 133, 146);
    	doc = tpCareer.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
    	main.getContentPane().add(tpCareer);
    	
    	tpSalary = new JTextPane();
    	tpSalary.setForeground(Color.WHITE);
    	tpSalary.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpSalary.setBackground(new Color (150,79,76));
    	tpSalary.setBounds(183, 85, 133, 146);
    	doc = tpSalary.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
    	main.getContentPane().add(tpSalary);
	}
	
	public void changeOrStay (CareerCard c, SalaryCard s) {
		String newLine = "\n";
		
		tpCareer.setText("CAREERCARD" + newLine + newLine + newLine + c.getCareerName() + newLine + newLine + newLine + "Max Raises" + newLine + c.getPayRaise());

		tpSalary.setText("SALARYCARD" + newLine + newLine + newLine + s.getSalary()     + newLine + newLine + newLine + "Tax"        + newLine + s.getTaxDue());
	
		
		main.repaint();
		main.revalidate();
		
		main.getContentPane().add(btnYes);
		main.getContentPane().add(btnNo);
		
		main.getContentPane().add(tpCareer);
		main.getContentPane().add(tpSalary);

	}
	
	public void dispose () {
		main.dispose();
	}
	
	public void setActionListener (ActionListener l) {
		btnYes.addActionListener(l);
		btnNo.addActionListener(l);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener(l);
	}
}
