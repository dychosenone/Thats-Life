package View;

import java.awt.Color;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.Career.CareerCard;
import Model.SalaryCard.SalaryCard;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
public class ChooseCareerUI {
	
	private JDialog main;
	
	private JButton btnCareer1;
	private JButton btnCareer2;
	
	private JTextPane tpCareer1;
	private JTextPane tpCareer2;
	
	private JTextPane tpSalary1;
	private JTextPane tpSalary2;
	
	public ChooseCareerUI() {
		main = new JDialog ();
		main.getContentPane().setBackground(Color.WHITE);
		
    	main.getContentPane().setLayout(null);
    	main.setBackground(Color.WHITE);
    	main.setLocationRelativeTo(null);
    	
    	init();
    	
        main.setSize (741, 367);
        main.setResizable(false);
        main.setVisible (true);    
        main.setModal(true);
	}
	
	public void init () {
    	JLabel lblNewLabel = new JLabel("CHOOSE A CAREER");
    	lblNewLabel.setForeground(Color.BLACK);
    	lblNewLabel.setFont(new Font("Quicksand Light", Font.PLAIN, 30));
    	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel.setBounds(10, 11, 715, 63);
    	main.getContentPane().add(lblNewLabel);
    	
    	btnCareer1 = new JButton("Career 1");
    	btnCareer1.setForeground(Color.WHITE);
    	btnCareer1.setBackground(new Color(105,102,103));
    	btnCareer1.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnCareer1.setBounds(42, 108, 290, 49);
//    	main.getContentPane().add(btnCareer1);
    	
    	btnCareer2 = new JButton("Career 2");
    	btnCareer2.setForeground(Color.WHITE);
    	btnCareer2.setBackground(new Color(105,102,103));
    	btnCareer2.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnCareer2.setBounds(403, 108, 290, 49);
//    	main.getContentPane().add(btnCareer2);
    	
    	StyledDocument doc;
		SimpleAttributeSet center = new SimpleAttributeSet();
    	
    	tpCareer1 = new JTextPane();
    	tpCareer1.setForeground(Color.WHITE);
    	tpCareer1.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpCareer1.setBackground(new Color (86,117,114));
    	tpCareer1.setBounds(42, 168, 133, 146);
    	doc = tpCareer1.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
//    	main.getContentPane().add(tpCareer1);
    	
    	tpSalary1 = new JTextPane();
    	tpSalary1.setForeground(Color.WHITE);
    	tpSalary1.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpSalary1.setBackground(new Color (150,79,76));
    	tpSalary1.setBounds(199, 168, 133, 146);
    	doc = tpSalary1.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
//    	main.getContentPane().add(tpSalary1);
    	
    	tpCareer2 = new JTextPane();
    	tpCareer2.setForeground(Color.WHITE);
    	tpCareer2.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpCareer2.setBackground(new Color (86,117,114));
    	tpCareer2.setBounds(403, 168, 133, 146);
    	doc = tpCareer2.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
//    	main.getContentPane().add(tpCareer2);
    	
    	tpSalary2 = new JTextPane();
    	tpSalary2.setForeground(Color.WHITE);
    	tpSalary2.setFont(new Font("Quicksand Light", Font.PLAIN, 12));
    	tpSalary2.setBackground(new Color (150,79,76));
    	tpSalary2.setBounds(560, 168, 133, 146);
    	doc = tpSalary2.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
//    	main.getContentPane().add(tpSalary2);
	}
	
	public void chooseCareer (CareerCard c1, SalaryCard s1, CareerCard c2, SalaryCard s2) {
		String newLine = "\n";
		
		btnCareer1.setText(c1.getCareerName());
		btnCareer2.setText(c2.getCareerName());
		
		tpCareer1.setText("CAREERCARD" + newLine + newLine + newLine + c1.getCareerName() + newLine + newLine + newLine + "Max Raises" + newLine + c1.getPayRaise());
		tpCareer2.setText("CAREERCARD" + newLine + newLine + newLine + c2.getCareerName() + newLine + newLine + newLine + "Max Raises" + newLine + c2.getPayRaise());
		
		tpSalary1.setText("SALARYCARD" + newLine + newLine + newLine + s1.getSalary()     + newLine + newLine + newLine + "Tax"        + newLine + s1.getTaxDue());
		tpSalary2.setText("SALARYCARD" + newLine + newLine + newLine + s2.getSalary()     + newLine + newLine + newLine + "Tax"        + newLine + s2.getTaxDue());
		
		main.repaint();
		main.revalidate();
		
		main.getContentPane().add(btnCareer1);
		main.getContentPane().add(btnCareer2);
		
		main.getContentPane().add(tpCareer1);
		main.getContentPane().add(tpCareer2);
		
		main.getContentPane().add(tpSalary1);
		main.getContentPane().add(tpSalary2);
	}
	
	public void dispose () {
		main.dispose();
	}
	
	public void setActionListener (ActionListener l) {
		btnCareer1.addActionListener(l);
		btnCareer2.addActionListener(l);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener(l);
	}
}
