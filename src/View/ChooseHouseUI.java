package View;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Model.HouseCard.HouseCard;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class ChooseHouseUI {
	private JDialog main;
	
	private JButton btnProperty1;
	private JButton btnProperty2;
	private JButton btnProperty3;
	private JButton btnProperty4;
	private JButton btnProperty5;
	private JButton btnProperty6;
	
	private JLabel lblPropertyPrice1;
	private JLabel lblPropertyPrice2;
	private JLabel lblPropertyPrice3;
	private JLabel lblPropertyPrice4;
	private JLabel lblPropertyPrice5;
	private JLabel lblPropertyPrice6;
	
	public ChooseHouseUI(){
		main = new JDialog ();
    	main.getContentPane().setBackground(Color.WHITE);
    	main.getContentPane().setLayout(null);
    	
    	init();
    	
        main.setSize (545, 367);
        main.setResizable(false);
        main.setVisible (true);    
        main.setModal(true);
	}
	
	public void init () {
		
		btnProperty1 = new JButton("1");
		btnProperty1.setForeground(Color.WHITE);
		btnProperty1.setBackground(new Color(5, 30, 62));
		btnProperty1.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty1.setBounds(22, 66, 158, 86);
    	
    	btnProperty2 = new JButton("2");
    	btnProperty2.setForeground(Color.WHITE);
    	btnProperty2.setBackground(new Color (37, 30, 62));
    	btnProperty2.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty2.setBounds(190, 66, 158, 86);
    	
    	btnProperty3 = new JButton("3");
    	btnProperty3.setForeground(Color.WHITE);
    	btnProperty3.setBackground(new Color (69, 30, 62));
    	btnProperty3.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty3.setBounds(358, 66, 158, 86);
    	
    	btnProperty4 = new JButton("4");
    	btnProperty4.setForeground(Color.WHITE);
    	btnProperty4.setBackground(new Color (101, 30, 62));
    	btnProperty4.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty4.setBounds(22, 200, 158, 88);
    	
    	btnProperty5 = new JButton("5");
    	btnProperty5.setForeground(Color.WHITE);
    	btnProperty5.setBackground(new Color (133, 30, 62));
    	btnProperty5.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty5.setBounds(190, 200, 158, 88);
    	
    	btnProperty6 = new JButton("6");
    	btnProperty6.setForeground(Color.WHITE);
    	btnProperty6.setBackground(new Color (74, 78, 77));
    	btnProperty6.setFont(new Font("Courier New", Font.PLAIN, 14));
    	btnProperty6.setBounds(358, 200, 158, 88);
    	   	
    	JLabel lblInstructions = new JLabel("BUY A HOUSE");
    	lblInstructions.setForeground(Color.BLACK);
    	lblInstructions.setFont(new Font("Courier New", Font.PLAIN, 22));
    	lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInstructions.setBounds(154, 16, 249, 39);
    	
    	main.getContentPane().add(lblInstructions);
    	
    	lblPropertyPrice1 = new JLabel("1");
    	lblPropertyPrice1.setForeground(new Color(5, 30, 62));
    	lblPropertyPrice1.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice1.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice1.setBounds(22, 156, 158, 33);
    	
    	lblPropertyPrice2 = new JLabel("2");
    	lblPropertyPrice2.setForeground(new Color (37, 30, 62));
    	lblPropertyPrice2.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice2.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice2.setBounds(190, 156, 158, 33);
    	
    	lblPropertyPrice3 = new JLabel("3");
    	lblPropertyPrice3.setForeground(new Color (69, 30, 62));
    	lblPropertyPrice3.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice3.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice3.setBounds(358, 156, 158, 33);
    	
    	lblPropertyPrice4 = new JLabel("4");
    	lblPropertyPrice4.setForeground(new Color (101, 30, 62));
    	lblPropertyPrice4.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice4.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice4.setBounds(22, 294, 158, 33);
    	
    	lblPropertyPrice5 = new JLabel("5");
    	lblPropertyPrice5.setForeground(new Color (133, 30, 62));
    	lblPropertyPrice5.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice5.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice5.setBounds(190, 294, 158, 33);
    	
    	lblPropertyPrice6 = new JLabel("6");
    	lblPropertyPrice6.setForeground(new Color (74, 78, 77));
    	lblPropertyPrice6.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPropertyPrice6.setFont(new Font("Courier New", Font.PLAIN, 18));
    	lblPropertyPrice6.setBounds(358, 294, 158, 33);
	}
	
	public void chooseHouse (ArrayList <HouseCard> houses) {
		int i;
		
		for (i = 0; i < houses.size(); i ++) {
			switch (i) {
				case 0:
					System.out.print(i);
					if(houses.get(i).isAvailable()){
						btnProperty1.setText(houses.get(i).getHouseName());
						lblPropertyPrice1.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty1);
						main.getContentPane().add(lblPropertyPrice1);
						
					}
					break;
				case 1:
					if(houses.get(i).isAvailable()){
						btnProperty2.setText(houses.get(i).getHouseName());
						lblPropertyPrice2.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty2);
						main.getContentPane().add(lblPropertyPrice2);
					}
					break;
				case 2:
					if(houses.get(i).isAvailable()){
						btnProperty3.setText(houses.get(i).getHouseName());
						lblPropertyPrice3.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty3);
						main.getContentPane().add(lblPropertyPrice3);
					}
					break;
				case 3:
					if(houses.get(i).isAvailable()){
						btnProperty4.setText(houses.get(i).getHouseName());
						lblPropertyPrice4.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty4);
						main.getContentPane().add(lblPropertyPrice4);
					}
					break;
				case 4:
					if(houses.get(i).isAvailable()){
						btnProperty5.setText(houses.get(i).getHouseName());
						lblPropertyPrice5.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty5);
						main.getContentPane().add(lblPropertyPrice5);
					}
					break;
				case 5:
					if(houses.get(i).isAvailable()){
						btnProperty6.setText(houses.get(i).getHouseName());
						lblPropertyPrice6.setText("PRICE : " + houses.get(i).getValue());
						
						main.revalidate();
						main.repaint();
						main.getContentPane().add(btnProperty6);
						main.getContentPane().add(lblPropertyPrice6);
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
	
	public void setWindowListeners (WindowListener l) {
		main.addWindowListener(l);
	}
	 
	public void dispose () {
		main.dispose();
	}
}
