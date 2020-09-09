package View;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import Model.Player.Player;
import Model.Player.Players;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame{;
    private JButton btnWheel;
    private JButton btnCollect;
    private JButton btnPay;
    
    
    private JTextField tfInput;
    
    private ConsolePanel console = new ConsolePanel ();
    
    private JScrollPane scroll;
    
    private JTextArea taConsole;
    private JTextArea taPlayerInfo;
    
    private boolean done = false;
    
    //GridBagConstraints c = new GridBagConstraints ();

    public GUI () {
    	
        super ("That's Life");
        getContentPane().setBackground(new Color(211, 211, 211));
        getContentPane().setForeground(new Color(119, 136, 153));
        getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        init();

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize (1280, 720);
        setResizable(false);
        setVisible (true);
    }

    private void init () {
    	
    	getContentPane().setLayout(null);
    		 	 	 	 	 	 	
    	btnWheel = new JButton ("SPIN WHEEL");
    	btnWheel.setForeground(new Color(105, 105, 105));
    	btnWheel.setBackground(new Color(255, 235, 205));
    	//btnWheel.setOpaque(true);
    	btnWheel.setFont(new Font("Courier New", Font.PLAIN, 18));
    	btnWheel.setBounds(792, 627, 153, 54);
    	 	 	 	 	 	 
    	getContentPane().add (btnWheel);
    	//getContentPane().add(console);
    	
    	tfInput = new JTextField();
    	tfInput.setForeground(new Color(248, 248, 255));
    	tfInput.setBackground(new Color(69, 69, 69));
    	tfInput.setBounds(792, 585, 462, 31);
    	tfInput.setEditable(true);
    	getContentPane().add(tfInput);
    	
    	tfInput.setColumns(10);
    	
    	taPlayerInfo = new JTextArea();
    	taPlayerInfo.setLineWrap(true);
    	taPlayerInfo.setForeground(new Color(255, 255, 255));
    	taPlayerInfo.setBackground(new Color(92, 64, 51));
    	taPlayerInfo.setFont(new Font("Courier New", Font.PLAIN, 20));
    	taPlayerInfo.setWrapStyleWord(true);
    	taPlayerInfo.setEditable(false);
    	taPlayerInfo.setBounds(792, 35, 462, 87);
    	
    	getContentPane().add(taPlayerInfo);
    	
    	taConsole = new JTextArea();
    	taConsole.setFont(new Font("Courier New", Font.PLAIN, 16));
    	taConsole.setForeground(new Color(255, 255, 255));
    	taConsole.setBackground(new Color(92, 64, 51));
    	taConsole.setWrapStyleWord(true);
    	taConsole.setEditable(false);
    	
    	DefaultCaret caret = (DefaultCaret)taConsole.getCaret();
    	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    	
    	scroll = new JScrollPane (taConsole);
    	scroll.setBounds(792, 125, 462, 450);
    	
    	getContentPane().add(scroll);
    	
    	btnCollect = new JButton("COLLECT");
    	btnCollect.setForeground(new Color(105, 105, 105));
    	btnCollect.setBackground(new Color(255, 235, 205));
    	btnCollect.setFont(new Font("Courier New", Font.PLAIN, 20));
    	btnCollect.setBounds(947, 627, 152, 54);
    	getContentPane().add(btnCollect);
    	
    	btnPay = new JButton("PAY");
    	btnPay.setForeground(new Color(105, 105, 105));
    	btnPay.setBackground(new Color(255, 235, 205));
    	btnPay.setFont(new Font("Courier New", Font.PLAIN, 20));
    	btnPay.setBounds(1101, 627, 153, 54);
    	getContentPane().add(btnPay);
    	
    	JLabel lblPlayerData = new JLabel("PLAYER DATA");
    	lblPlayerData.setForeground(new Color(69, 69, 69));
    	lblPlayerData.setFont(new Font("Times New Roman", Font.BOLD, 19));
    	lblPlayerData.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPlayerData.setBounds(901, 8, 250, 16);
    	getContentPane().add(lblPlayerData);
    	
    }
    
    public void setListener (ActionListener listener) {
    	btnWheel.addActionListener(listener);
    	btnCollect.addActionListener (listener);
    	btnPay.addActionListener (listener);
    }
    
    public void setKeyListener (KeyListener listener) {
    	tfInput.addKeyListener(listener);
    }
    
    public void displayText (String text) {
    	taConsole.append(">> " + text + "\n");
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
    
    public void updatePlayerInfo (ArrayList<Player> players, Player currentPlayer) {
    	
    	taPlayerInfo.setText("");
    	int i= 0;
    	for (i = 0; i < players.size(); i++){     
    		taPlayerInfo.append(players.get(i).getName() + " : " +  players.get(i).getBalance() + "\n");
    	}
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
    
    public void interactSpace (int spaceType) {                    //INDEX
    	String text[] = {  "YOU LANDED ON ACTION CARD TILE",       //0
    					   "YOU LANDED ON JOB SEARCH TILE",        //1
    					   "YOU LANDED ON GET MARRIED TILE",};     //2
    	
    	
    	displayText(text[spaceType]);
    }
    
    public void choosePlayer (ArrayList<Player> players, Player currentPlayer) {
    	for (int i = 0; i < players.size(); i++) {
    		
    		if (!players.get(i).equals(currentPlayer))
    			displayText("[" + (i+1) + "] " + players.get(i).getName());
    	}
    }
}
