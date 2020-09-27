package View;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowListener;

public class ConsoleUI {
	
	private JDialog main;
	private JLabel message;
	
	public ConsoleUI(){
		
		
		main = new JDialog ();
		main.getContentPane().setBackground(new Color (42, 54, 59));
		main.getContentPane().setLayout(null);
		main.setLocationRelativeTo(null);
		
		
		init();

		main.setSize (400, 155);
		main.setVisible (true);
		main.setResizable (false);
		main.setModal(true);
		main.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
	
	public void init() {
		
		message = new JLabel("New Text");
		message.setFont(new Font("Courier New", Font.PLAIN, 20));
		message.setForeground(Color.WHITE);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(10, 11, 374, 104);
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
