package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoosePathUI{
	
	private JDialog main;

	private JButton btnPath1;
	private JButton btnPath2;
	
	private JLabel lblChoose;
	
	public ChoosePathUI () {
		
		main = new JDialog ();
		main.getContentPane().setBackground(new Color (42, 54, 59));
		main.getContentPane().setForeground(Color.WHITE);
		main.setLocationRelativeTo(null);
			
		initialize ();
		
		main.setSize (400, 200);
		main.setResizable(false);
		main.getContentPane().setLayout(null);
		main.setModal(true);
		
	}
	
	public void initialize () {
		
		btnPath1 = new JButton("1");
		btnPath1.setBackground(new Color (254, 206, 168));
		btnPath1.setForeground(new Color (42, 54, 59));
		btnPath1.setFont(new Font("Quicksand Light", Font.PLAIN, 14));
		btnPath1.setBounds(31, 71, 161, 74);
		
		btnPath2 = new JButton("2");
		btnPath2.setBackground(new Color (255, 132, 124));
		btnPath2.setForeground(new Color (42, 54, 59));
		btnPath2.setFont(new Font("Quicksand Light", Font.PLAIN, 14));
		btnPath2.setBounds(202, 71, 161, 74);
		
		lblChoose = new JLabel("WHICH PATH WILL YOU TAKE");
		lblChoose.setForeground(Color.WHITE);
		lblChoose.setFont(new Font("Quicksand Light", Font.PLAIN, 20));
		lblChoose.setBounds(54, 11, 298, 49);
		main.setVisible (true);
	}
	
	public void choosePath (String path1, String path2) {
		btnPath1.setText(path1);
		btnPath2.setText(path2);
		
		main.revalidate();
		main.repaint();
		main.getContentPane().add(btnPath1);
		
		main.revalidate();
		main.repaint();
		main.getContentPane().add(btnPath2);
		
		main.revalidate();
		main.repaint();
		main.getContentPane().add(lblChoose);
		
	}
	
	public void dispose() {
		main.dispose();
	}
	
	public void setActionListener (ActionListener listener) {
		btnPath1.addActionListener(listener);
		btnPath2.addActionListener(listener);
	}
	
	public void setWindowListener (WindowListener l) {
		main.addWindowListener(l);
	}
}
