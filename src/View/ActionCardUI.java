package View;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Model.ActionCard.ActionCard;
import javax.swing.JInternalFrame;

public class ActionCardUI {
	
	private JDialog main;
	
	private JLabel lblCardName;
	private JLabel lblCardValue;
	private	JLabel lblInstructions;
	
	public ActionCardUI() {
		main = new JDialog ();
		main.setTitle("ACTION CARD");
		main.getContentPane().setBackground(new Color (255,140,0));
		main.getContentPane().setLayout(null);
		
		main.setLocationRelativeTo(null);
		
		
		init();

		main.setSize (220, 237);
		main.setVisible (true);
		main.setResizable (false);
		main.setModal(true);
		main.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
	
	public void init() {
		lblCardName = new JLabel("CardName");
		lblCardName.setForeground(Color.BLACK);
		lblCardName.setFont(new Font("Quicksand Light", Font.BOLD, 24));
		lblCardName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardName.setBounds(10, 24, 194, 33);
		
		lblCardValue = new JLabel("CardValue");
		lblCardValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardValue.setForeground(Color.BLACK);
		lblCardValue.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
		lblCardValue.setBounds(10, 79, 194, 33);
		
		lblInstructions = new JLabel("CardInstructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setForeground(Color.BLACK);
		lblInstructions.setFont(new Font("Quicksand Light", Font.PLAIN, 14));
		lblInstructions.setBounds(10, 146, 194, 33);
	}
	
	public void displayCard (ActionCard c, String instructions) {
		String s = Integer.toString(c.getValue());
		
		main.revalidate();
		main.repaint();
		
		lblCardName.setText(c.getCardName());
		lblCardValue.setText(s);
		lblInstructions.setText(instructions);
		
		main.getContentPane().add(lblCardName);
		main.getContentPane().add(lblCardValue);
		main.getContentPane().add(lblInstructions);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener(l);
	}	
}
