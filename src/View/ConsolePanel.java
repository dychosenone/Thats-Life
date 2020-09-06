package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsolePanel extends JPanel{
	
	JTextArea taConsole;
	JScrollPane spScroll = new JScrollPane (taConsole);
	
	public ConsolePanel () {
		
		taConsole = new JTextArea(462, 563);
    	taConsole.setEditable(false);
    	setVisible(true);
    	
 //   	taConsole.setFont("Times New Roman", Font.PLAIN, 14);
    	
    	setBounds(1,2,3,4);
    	setLayout(new BorderLayout());
    	add(spScroll, BorderLayout.CENTER);
	}
	
	public void displayText (String text) {
    	taConsole.append(taConsole.getText() + "\n" + ">> " + text + "\n");
    }
}
