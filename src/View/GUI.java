package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{;
    private JButton btnWheel;
    private JTextField tfInput;
    private ConsolePanel console = new ConsolePanel ();
    private JTextArea taConsole;
    private JButton btnEnter;
    
    //GridBagConstraints c = new GridBagConstraints ();

    public GUI () {
    	
        super ("That's Life");
        
        init();

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize (1280, 720);
        setResizable(false);
        setVisible (true);
    }

    private void init () {
    	
    	getContentPane().setLayout(null);
    		 	 	 	 	 	 	
    	btnWheel = new JButton ("Spin Wheel");
    	btnWheel.setBounds(942, 627, 174, 54);
    	 	 	 	 	 	 
    	getContentPane().add (btnWheel);
    	//getContentPane().add(console);
    	
    	tfInput = new JTextField();
    	tfInput.setBounds(792, 585, 462, 31);
    	tfInput.setEditable(false);
    	getContentPane().add(tfInput);
    	
    	tfInput.setColumns(10);
    	
    	taConsole = new JTextArea();
    	taConsole.setWrapStyleWord(true);
    	taConsole.setEditable(false);
    	taConsole.setBounds(792, 11, 462, 563);
    	getContentPane().add(taConsole);
    	
    	btnEnter = new JButton("Enter");
    	btnEnter.setBounds(792, 627, 149, 54);
    	getContentPane().add(btnEnter);
    	
    	/*JPanel pConsole = new JPanel();
    	pConsole.setLayout(new BorderLayout());
    	pConsole.setBounds(792, 11, 462, 563);
    	getContentPane().add(pConsole);
    	pConsole.add(console, BorderLayout.CENTER);*/

		JPanel board = new JPanel();
		
    }
    
    public void setListener (ActionListener listener) {
    	btnWheel.addActionListener(listener);
    	btnEnter.addActionListener(listener);
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
		displayText(text);
		tfInput.setText("");
		return text;
	}
    
    public void getNumberOfPlayers () {
    	String input;
    	
    	tfInput.setEditable(true);
    	
    	displayText("Enter Number of Players: ");
    }
}
