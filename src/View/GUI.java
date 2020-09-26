package View;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.Player.Career;
import Model.Player.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI{;
    private static final Career NULL = null;
    
    private JFrame main;
    
	private JButton btnWheel;
    private JButton btnPayLoan;
    private JButton btnGetLoan;
    private JButton btnEndTurn;

    private PlayerInfoUI playerOneInfo;
    private PlayerInfoUI playerTwoInfo;
    private PlayerInfoUI playerThreeInfo;
    
    private JTextField tfInput;

    private JScrollPane scroll;
    private JTextPane taConsole;
    
    private JTextPane tpWheel;

    private BoardGUI board;
    
    private boolean done = false;

    //GridBagConstraints c = new GridBagConstraints ();

    public GUI () {
        
        System.out.println("GUI START");
        
        main = new JFrame ("That's Life");
        
        main.revalidate();
        main.repaint();
        
        main.getContentPane().setBackground(new Color (42, 54, 59));
        main.getContentPane().setForeground(new Color(119, 136, 153));

        init();

        main.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        main.setSize (1280, 720);
        main.setResizable(false);
        main.setVisible (true);
    }

    private void init () {

    	main.getContentPane().setLayout(null);
    	
    	board = new BoardGUI();
    	board.setBounds(16, 20, 764, 595);
    	board.setVisible(true);
    	main.getContentPane().add(board);
    	
    	playerOneInfo = new PlayerInfoUI (new Color(41, 40, 38), new Color (249, 211, 66));
    	playerOneInfo.setBounds(790, 20, 469, 181);
    	playerOneInfo.setVisible(true);
    	
    	playerTwoInfo = new PlayerInfoUI (new Color(74, 39, 79), new Color (240, 160, 124));
    	playerTwoInfo.setBounds(790, 205, 469, 181);
    	playerTwoInfo.setVisible(true);
    	
    	playerThreeInfo = new PlayerInfoUI (new Color(47, 60, 126), new Color (251, 234, 235));
    	playerThreeInfo.setBounds(790, 391, 469, 181);
    	playerThreeInfo.setVisible(true);
    	
    	main.getContentPane().add(playerOneInfo);
    	main.getContentPane().add(playerTwoInfo);
    	main.getContentPane().add(playerThreeInfo);

    	btnWheel = new JButton ("SPIN WHEEL");
    	btnWheel.setForeground(new Color (42, 54, 59));
    	btnWheel.setBackground(new Color(153,184,152));
    	btnWheel.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnWheel.setBounds(22, 627, 180, 54);
    	
    	main.getContentPane().add (btnWheel);

    	btnPayLoan = new JButton ("PAY LOAN");
    	btnPayLoan.setForeground(new Color (42, 54, 59));
    	btnPayLoan.setBackground(new Color(255, 132, 124));
    	btnPayLoan.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnPayLoan.setBounds(402, 627, 180, 54);

    	main.getContentPane().add (btnPayLoan);

    	btnGetLoan = new JButton ("GET LOAN");
    	btnGetLoan.setForeground(new Color (42, 54, 59));
    	btnGetLoan.setBackground(new Color(254, 206, 168));
    	btnGetLoan.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnGetLoan.setBounds(212, 627, 180, 54);

    	main.getContentPane().add (btnGetLoan);

    	btnEndTurn = new JButton ("END TURN");
    	btnEndTurn.setForeground(new Color (42, 54, 59));
    	btnEndTurn.setBackground(new Color(232,72,95));
    	btnEndTurn.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	btnEndTurn.setBounds(592, 627, 180, 54);

    	main.getContentPane().add (btnEndTurn);

    	tfInput = new JTextField();
    	tfInput.setForeground(new Color (42, 54, 59));
    	tfInput.setBackground(Color.WHITE);
    	tfInput.setBounds(790, 649, 469, 31);
    	tfInput.setEditable(true);
    	main.getContentPane().add(tfInput);

    	tfInput.setColumns(10);
    	
    	StyledDocument doc;
		SimpleAttributeSet center = new SimpleAttributeSet();

    	taConsole = new JTextPane();
    	taConsole.setFont(new Font("Quicksand Light", Font.PLAIN, 18));
    	taConsole.setForeground(new Color (42, 54, 59));
    	taConsole.setBackground(Color.WHITE);
    	taConsole.setEditable(false);
    	doc = taConsole.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

    	DefaultCaret caret = (DefaultCaret)taConsole.getCaret();
    	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    	scroll = new JScrollPane (taConsole);
    	scroll.setBounds(790, 575, 369, 70);

    	main.getContentPane().add(scroll);
    	
    	tpWheel = new JTextPane();
    	tpWheel.setFont(new Font("Quicksand Light", Font.PLAIN, 24));
    	tpWheel.setBackground(Color.WHITE);
    	tpWheel.setBounds(1169, 575, 90, 70);
    	doc = tpWheel.getStyledDocument();
    	StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
    	main.getContentPane().add(tpWheel);
    	
    	
    }

    public BoardGUI getBoard () {
    	return this.board;

    }
    
    public void dispose () {
    	main.dispose();
    }

    public void setListener (ActionListener listener) {
    	btnWheel.addActionListener(listener);
    	btnPayLoan.addActionListener(listener);
    	btnGetLoan.addActionListener(listener);
    	btnEndTurn.addActionListener(listener);
    }

    public void setKeyListener (KeyListener listener) {
    	tfInput.addKeyListener(listener);
    }

    public void displayText (String text) {
    	taConsole.setText(text + "\n");
    }

    public void nextTurn () {
    	taConsole.setText("");
    }

    public String getInput (){

		String text = tfInput.getText();
		tfInput.selectAll();
		//displayText(text);
		tfInput.setText("");
		done = true;
		//closeInput();
		return text;
	}

    public void updatePlayerInfo (ArrayList<Player> players) {

    	int i;

    	for (i = 1; i <= players.size(); i++){
    		
    		switch (i) {
    		case 1:
    			playerOneInfo.updateUserInfo(players.get(i-1));
    			break;
    		case 2:
    			playerTwoInfo.updateUserInfo(players.get(i-1));
    			break;
    		case 3:
    			playerThreeInfo.updateUserInfo(players.get(i-1));
    			break;
    		}
    	}
    }
    
    public void displayDice (int dice) {
    	String d = Integer.toString(dice);
    	tpWheel.setText("DICE\n" + d);
    }

    public void getNumberOfPlayers () {

    	done = false;
    	displayText("Enter Number of Players: ");
    }

    public void inputPlayers(int i) {

    	done = false;
    	displayText ("INPUT NAME OF PLAYER " + (i+1));
    }

    public boolean isDone () {
    	return done;
    }

    public void enableInputs () {
    	tfInput.setEditable(true);
    }

    public void disableInputs () {
    	tfInput.setEditable(false);
    }
    
    public void decideWinner (Player winner) {
    	
    }

    public void interactSpace (int spaceType) {                       //INDEX
    	String text[] = {  "YOU LANDED ON ACTION CARD TILE",          //0
    					   "YOU LANDED ON JOB SEARCH TILE",           //1
    					   "YOU LANDED ON GET MARRIED TILE",	      //2
    					   "YOU LANDED ON CHOOSE PATH TILE",	      //3
    					   "YOU LANDED ON HAVE CHILD TILE",		      //4
    					   "YOU LANDED ON BUY A HOUSE TILE",	      //5
    					   "YOU LANDED ON GRADUATION TILE",		      //6
    					   "YOU LANDED ON COLLEGE CAREER CHOICE TILE",//7
    					   "YOU LANDED ON BLUE CARD TILE",		      //8
    					   "YOU LANDED ON GREEN TILE"};               //9

    	if (spaceType != -1) {
	    	System.out.println(spaceType);
	    	displayText(text[spaceType]);
    	}
    	else {
    		displayText("YOU LANDED ON NA");
    	}
    }
}
