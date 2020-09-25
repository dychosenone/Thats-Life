package View;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowListener;

public class consoleUI {
	
	private JDialog main;
	private JLabel message;
	
	public consoleUI(){
		
		System.out.println("CONSOLE UI");
		
		main = new JDialog ();
		System.out.println("CONSOLE UI");
		main.getContentPane().setBackground(Color.WHITE);
		System.out.println("CONSOLE UI");
		main.getContentPane().setLayout(null);
		System.out.println("CONSOLE UI");
		init();

		main.setSize (400, 155);
		main.setVisible (true);
		main.setResizable (false);
	}
	
	public void init() {
		
		message = new JLabel("New label");
		message.setFont(new Font("Courier New", Font.PLAIN, 25));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(10, 35, 364, 45);
		
	}
	
	public void displayMessage (String m) {
		
		main.revalidate();
		main.repaint();
		
		message.setText(m);
		
		main.getContentPane().add(message);
		
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener(l);
	}
	
}
