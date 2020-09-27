package View;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;

public class InputDialog {
	
	private JDialog main;
	
	private JTextField tfInput;
	
	private JLabel lblInstructions;
	
	public InputDialog () {
		
		main = new JDialog ();
		main.getContentPane().setBackground(new Color (42, 54, 59));
		main.getContentPane().setLayout(null);
		main.setLocationRelativeTo(null);
		
		init();
		
		main.setSize (343, 187);
		main.setVisible (true);
		main.setResizable (false);
	}
	
	public void init() {
		lblInstructions = new JLabel("Instructions");
		lblInstructions.setForeground(Color.WHITE);
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblInstructions.setBounds(20, 11, 300, 45);
		main.getContentPane().add(lblInstructions);
		
		tfInput = new JTextField();
		tfInput.setBackground(Color.white);
		tfInput.setForeground(new Color (42, 54, 59));
		tfInput.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		tfInput.setBounds(20, 67, 300, 38);
		main.getContentPane().add(tfInput);
		tfInput.setColumns(10);	
	}
	
	public void showDialog (String instructions) {
		
		main.revalidate();
		main.repaint();
		
		lblInstructions.setText(instructions);
		main.getContentPane().add(lblInstructions);
	}
	
	public void dispose() {
		main.dispose();
	}
	
	public String getInput (){

		String text = tfInput.getText();
		tfInput.selectAll();
		tfInput.setText("");
		
		return text;
	}
	
	public void setKeyListener (KeyListener l) {
		tfInput.addKeyListener(l);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener (l);
	}
}
