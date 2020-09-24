package View;
import javax.swing.*;
import java.awt.event.*;

public class MainMenu{
	
	private JFrame mainFrame;
	private JButton btnStart;
	private JButton btnInstructions;
	private JButton btnExit;
	
	public MainMenu () {
		
		mainFrame = new JFrame ("Game Of Life");
		mainFrame.getContentPane().setLayout(null);
		
		init ();
		
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize (400, 300);
		mainFrame.setVisible (true);
		mainFrame.setResizable (false);
	}
	
	public void init() {
		btnStart = new JButton("START GAME");
		btnStart.setBounds(105, 85, 171, 50);
		mainFrame.add(btnStart);
		
		btnInstructions = new JButton("INSTRUCTIONS");
		btnInstructions.setBounds(105, 141, 171, 50);
		mainFrame.add(btnInstructions);
		
		btnExit = new JButton("EXIT");
		btnExit.setBounds(105, 202, 171, 50);
		mainFrame.add(btnExit);
	}
	
	public void setActionListener (ActionListener l) {
		
		btnStart.addActionListener(l);
		btnInstructions.addActionListener(l);
		btnExit.addActionListener(l);
	}
}
