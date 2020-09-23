package View;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import Model.Player.Career;
import Model.Player.Player;
import Model.Player.Players;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame{;
    private static final Career NULL = null;
	private JButton btnWheel;
    private JButton btnPayLoan;
    private JButton btnGetLoan;
    private JButton btnEndTurn;


    private JTextField tfInput;

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
    	btnWheel.setBounds(22, 627, 180, 54);

    	getContentPane().add (btnWheel);
    	//getContentPane().add(console);

    	btnPayLoan = new JButton ("PAY LOAN");
    	btnPayLoan.setForeground(new Color(105, 105, 105));
    	btnPayLoan.setBackground(new Color(255, 235, 205));
    	btnPayLoan.setFont(new Font("Courier New", Font.PLAIN, 18));
    	btnPayLoan.setBounds(402, 627, 180, 54);

    	getContentPane().add (btnPayLoan);

    	btnGetLoan = new JButton ("GET LOAN");
    	btnGetLoan.setForeground(new Color(105, 105, 105));
    	btnGetLoan.setBackground(new Color(255, 235, 205));
    	btnGetLoan.setFont(new Font("Courier New", Font.PLAIN, 18));
    	btnGetLoan.setBounds(212, 627, 180, 54);

    	getContentPane().add (btnGetLoan);

    	btnEndTurn = new JButton ("END TURN");
    	btnEndTurn.setForeground(new Color(105, 105, 105));
    	btnEndTurn.setBackground(new Color(255, 235, 205));
    	btnEndTurn.setFont(new Font("Courier New", Font.PLAIN, 18));
    	btnEndTurn.setBounds(592, 627, 180, 54);

    	getContentPane().add (btnEndTurn);

    	tfInput = new JTextField();
    	tfInput.setForeground(new Color(248, 248, 255));
    	tfInput.setBackground(new Color(69, 69, 69));
    	tfInput.setBounds(792, 649, 462, 31);
    	tfInput.setEditable(true);
    	getContentPane().add(tfInput);

    	tfInput.setColumns(10);

    	taPlayerInfo = new JTextArea();
    	taPlayerInfo.setLineWrap(true);
    	taPlayerInfo.setForeground(new Color(255, 255, 255));
    	taPlayerInfo.setBackground(new Color(92, 64, 51));
    	taPlayerInfo.setFont(new Font("Courier New", Font.PLAIN, 18));
    	taPlayerInfo.setWrapStyleWord(true);
    	taPlayerInfo.setEditable(false);
    	taPlayerInfo.setBounds(792, 45, 462, 508);

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
    	scroll.setBounds(792, 565, 462, 75);

    	getContentPane().add(scroll);

    	JLabel lblPlayerData = new JLabel("PLAYER DATA");
    	lblPlayerData.setForeground(new Color(69, 69, 69));
    	lblPlayerData.setFont(new Font("Times New Roman", Font.BOLD, 19));
    	lblPlayerData.setHorizontalAlignment(SwingConstants.CENTER);
    	lblPlayerData.setBounds(901, 11, 250, 16);
    	getContentPane().add(lblPlayerData);

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
    	taConsole.append(text + "\n");
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

    public void updatePlayerInfo (ArrayList<Player> players, Player currentPlayer) {

    	taPlayerInfo.setText("");
    	String newLine = "\n";
    	int i;

    	for (i = 0; i < players.size(); i++){
    		taPlayerInfo.append(players.get(i).getName() + " : " +  players.get(i).getBalance() + newLine);
    		taPlayerInfo.append("POSITION       : " + players.get(i).getPosition() + newLine);
    		taPlayerInfo.append("MARRIED        : " + players.get(i).isMarried() + newLine);
    		taPlayerInfo.append("BABIES         : " + players.get(i).getBabies() + newLine);
    		
    		if (!players.get(i).isFinish()) {

	    		if (players.get(i).getJob() != NULL) {
		    		taPlayerInfo.append("CAREER POSITION: " + players.get(i).getJob().getPosition() + newLine);
		    		taPlayerInfo.append("CAREER SALARY  : " + players.get(i).getJob().getSalary() + newLine);
		    		taPlayerInfo.append("CAREER TAX     : " + players.get(i).getJob().getTax() + newLine);
	    		}
	    		else {
	    			taPlayerInfo.append("CAREER POSITION: " + "NONE" + newLine);
		    		taPlayerInfo.append("CAREER SALARY  : " + "NONE" + newLine);
		    		taPlayerInfo.append("CAREER TAX     : " + "NONE" + newLine);
	    		}
    		}
    		else {
    			taPlayerInfo.append("RETIRED" + newLine);
    		}
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
    					   "YOU LANDED ON GET MARRIED TILE",	   //2
    					   "YOU LANDED ON CHOOSE PATH TILE",	   //3
    					   "YOU LANDED ON HAVE CHILD TILE",		   //4
    					   "YOU LANDED ON BLUE CARD TILE",		   //5
    					   "YOU LANDED ON GREEN TILE"};            //6

    	if (spaceType != -1) {
	    	System.out.println(spaceType);
	    	displayText(text[spaceType]);
    	}
    	else {
    		displayText("YOU LANDED ON NA");
    	}
    }

    public void choosePlayer (ArrayList<Player> players, Player currentPlayer) {
    	taConsole.append("CHOOSE A PLAYER: " + "\n");
    	for (int i = 0; i < players.size(); i++) {

    		if (!players.get(i).equals(currentPlayer))
    			taConsole.append("[" + (i+1) + "] " + players.get(i).getName() + "\n");

    	}
    }
}
