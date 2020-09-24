package View;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Model.HouseCard.HouseCard;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseHouseUI {
	private JDialog main;
	
	private JButton btnProperty1;
	private JButton btnProperty2;
	private JButton btnProperty3;
	private JButton btnProperty4;
	private JButton btnProperty5;
	private JButton btnProperty6;
	
	public ChooseHouseUI(){
		main = new JDialog ();
    	main.getContentPane().setBackground(Color.GRAY);
    	main.getContentPane().setLayout(null);
    	
    	init();
    	
        main.setSize (545, 358);
        main.setResizable(false);
        main.setVisible (true);    
        main.setModal(true);
	}
	
	public void init () {
		btnProperty1 = new JButton("1");
		btnProperty1.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty1.setBounds(22, 66, 158, 121);
    	
    	btnProperty2 = new JButton("2");
    	btnProperty2.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty2.setBounds(190, 66, 158, 121);
    	
    	btnProperty3 = new JButton("3");
    	btnProperty3.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty3.setBounds(358, 66, 158, 121);
    	
    	btnProperty4 = new JButton("4");
    	btnProperty4.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty4.setBounds(22, 189, 158, 121);
    	
    	btnProperty5 = new JButton("5");
    	btnProperty5.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty5.setBounds(190, 189, 158, 121);
    	
    	btnProperty6 = new JButton("6");
    	btnProperty6.setFont(new Font("Courier New", Font.PLAIN, 16));
    	btnProperty6.setBounds(358, 189, 158, 121);
    	
    	JLabel lblInstructions = new JLabel("BUY A HOUSE");
    	lblInstructions.setForeground(Color.WHITE);
    	lblInstructions.setFont(new Font("Courier New", Font.PLAIN, 22));
    	lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInstructions.setBounds(154, 16, 249, 39);
    	
    	main.getContentPane().add(lblInstructions);
	}
	
	public void chooseHouse (ArrayList <HouseCard> houses) {
		int i;
		
		for (i = 0; i < houses.size(); i ++) {
			switch (i) {
				case 0:
					System.out.print(i);
					if(houses.get(i).isAvailable()){
						btnProperty1.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty1);
					}
					break;
				case 1:
					if(houses.get(i).isAvailable()){
						btnProperty2.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty2);
					}
					break;
				case 2:
					if(houses.get(i).isAvailable()){
						btnProperty3.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty3);
					}
					break;
				case 3:
					if(houses.get(i).isAvailable()){
						btnProperty4.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty4);
					}
					break;
				case 4:
					if(houses.get(i).isAvailable()){
						btnProperty5.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty5);
					}
					break;
				case 5:
					if(houses.get(i).isAvailable()){
						btnProperty6.setText(houses.get(i).getHouseName());
						main.getContentPane().add(btnProperty6);
					}
					break;
			}
		}
	}
	
	public void setActionListeners (ActionListener l) {
	    btnProperty1.addActionListener(l);
	    btnProperty2.addActionListener(l);
	    btnProperty3.addActionListener(l);
	    btnProperty4.addActionListener(l);
	    btnProperty5.addActionListener(l);
	    btnProperty6.addActionListener(l);
	}
	 
	public void dispose () {
		main.dispose();
	}
	
	
}
