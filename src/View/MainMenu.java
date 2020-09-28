package View;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class MainMenu{
	
	private JFrame mainFrame;
	private JButton btnStart;
	private JButton btnExit;
	
	public MainMenu () {
		
		mainFrame = new JFrame ("Game Of Life");
		mainFrame.getContentPane().setBackground(new Color (42, 54, 59));
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		
		init ();
		
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize (400, 252);
		mainFrame.setVisible (true);
		mainFrame.setResizable (false);
	}
	
	public void init() {
		btnStart = new JButton("START GAME");
		btnStart.setForeground(Color.WHITE);
		btnStart.setBackground(new Color (254, 206, 168));
		btnStart.setBounds(104, 85, 182, 50);
		mainFrame.getContentPane().add(btnStart);
		
		btnExit = new JButton("EXIT");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color (153, 184, 152));
		btnExit.setBounds(104, 146, 182, 50);
		mainFrame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("That's Life");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Quicksand Light", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 374, 63);
		mainFrame.getContentPane().add(lblNewLabel);
	}
	
	public void setActionListener (ActionListener l) {
		
		btnStart.addActionListener(l);
		btnExit.addActionListener(l);
	}
	
	public void dispose () {
		mainFrame.dispose();
	}
}
