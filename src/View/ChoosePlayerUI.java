package View;
import javax.swing.*;

import Model.Player.Players;
import Model.Player.Player;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class ChoosePlayerUI{
    
	int situation;
	private JDialog main;
	
	private JButton btnPlayer1;
	private JButton btnPlayer2;
	private JButton btnPlayer3;	
	
    public ChoosePlayerUI (int s) {
    	situation = s;
    	
    	main = new JDialog ();
    	main.getContentPane().setBackground(Color.GRAY);
    	
    	init();
    	
        main.setSize (350, 150);
        main.setResizable(false);
        
        main.setVisible (true);
        
        main.setModal(true);

    }
    
    public void init () {
    	
    	main.getContentPane().setLayout(null);
    	
        btnPlayer1 = new JButton("1");
        btnPlayer1.setBackground(new Color(41, 40, 38));
        btnPlayer1.setForeground(new Color (249, 211, 66));
        btnPlayer1.setBounds(17, 57, 100, 53);
        btnPlayer1.setFont(new Font("Courier New", Font.PLAIN, 18));
        
        btnPlayer2 = new JButton("2");
        btnPlayer2.setBackground(new Color(74, 39, 79));
        btnPlayer2.setForeground(new Color (240, 160, 124));
        btnPlayer2.setBounds(120, 57, 100, 53);
        btnPlayer2.setFont(new Font("Courier New", Font.PLAIN, 18));
        
        btnPlayer3 = new JButton("3");
        btnPlayer3.setBackground(new Color(47, 60, 126));
        btnPlayer3.setForeground(new Color (251, 234, 235));
        btnPlayer3.setBounds(222, 57, 100, 53);
        btnPlayer3.setFont(new Font("Courier New", Font.PLAIN, 18));
        
        JLabel lblQuestion = new JLabel ("CHOOSE A PLAYER");
        if (situation == 1) {
        	lblQuestion.setText("PAY A PLAYER");
        }
        
        else if (situation == 2) {
        	lblQuestion.setText("COLLECT FROM PLAYER");
        }
 
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuestion.setFont(new Font("Courier New", Font.PLAIN, 18));
        lblQuestion.setBounds(49, 22, 247, 14);
        main.getContentPane().add(lblQuestion);
        main.setVisible (true);
    }
    
    public void choosePlayer (ArrayList <Player> players, Player currentPlayer) {
    	int i;
    	
    	for(i = 1; i <= players.size(); i++) {
    		switch (i) {
    		case 1:
    			if(!currentPlayer.equals(players.get(i-1))) {
    				btnPlayer1.setText(players.get(i-1).getName());
    				main.getContentPane().add(btnPlayer1);
    			}
    			break;
    		case 2:
    			if(!currentPlayer.equals(players.get(i-1))) {
	    			btnPlayer2.setText(players.get(i-1).getName());
	    			main.getContentPane().add(btnPlayer2);
    			}
    			break;
    		case 3:
    			if(!currentPlayer.equals(players.get(i-1))) {
	    			btnPlayer3.setText(players.get(i-1).getName());
	    			main.getContentPane().add(btnPlayer3);
    			}
    			break;
    		}
    	}
    }
    
    public void dispose() {
    	main.dispose();
    }
    
    public void setActionListeners (ActionListener l) {
    	btnPlayer1.addActionListener(l);
    	btnPlayer2.addActionListener(l);
    	btnPlayer3.addActionListener(l);
    }
}