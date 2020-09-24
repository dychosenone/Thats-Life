package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoosePathUI{
	
	private JDialog main;

	private JButton btnPath1;
	private JButton btnPath2;
	private String path1;
	private String path2;
	
	public ChoosePathUI (String path1, String path2) {
		
		main = new JDialog ();
		
		this.path1 = path1;
		this.path2 = path2;
			
		initialize ();
		
		main.setSize (400, 200);
		main.setResizable(false);
		main.getContentPane().setLayout(null);
		
	}
	
	public void initialize () {
		
		btnPath1 = new JButton(path1);
		btnPath1.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnPath1.setBounds(31, 71, 161, 74);
		main.add(btnPath1);
		
		btnPath2 = new JButton(path2);
		btnPath2.setFont(new Font("Courier New", Font.PLAIN, 14));
		btnPath2.setBounds(202, 71, 161, 74);
		main.add(btnPath2);
		
		JLabel lblChoose = new JLabel("WHICH PATH WILL YOU TAKE");
		lblChoose.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblChoose.setBounds(54, 11, 298, 49);
		main.add(lblChoose);
		main.setVisible (true);
	}
	
	public void dispose() {
		main.dispose();
	}
	
	public void setListener (ActionListener listener) {
		btnPath1.addActionListener(listener);
		btnPath2.addActionListener(listener);
	}
}
