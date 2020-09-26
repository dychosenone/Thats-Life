package View;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.SalaryCard.SalaryCard;
import Model.Player.Career;
import Model.Player.House;
import Model.Player.Player;
import java.awt.Color;
import java.awt.Font;

public class PlayerInfoUI extends JPanel{
	
	private static final Career NULL = null;
	
	private Color primaryColor;
	private Color secondaryColor;
	
	private JTextArea playerInfo1;
	private JTextArea playerInfo2;
	private JTextPane playerName;
	private JTextPane careerInfo;
	private JTextPane salaryInfo;
	private JTextPane houseInfo;
	
	public PlayerInfoUI (Color pc, Color sc) {
		setBackground(sc);
		primaryColor = pc;
		secondaryColor = sc;
		
		init ();
		
		setLayout(null);
	}
	
	public void init () {
		
		StyledDocument doc;
		SimpleAttributeSet center = new SimpleAttributeSet();
		
		playerName = new JTextPane();
		playerName.setEditable(false);
		playerName.setForeground(secondaryColor);
		playerName.setFont(new Font("Courier New", Font.PLAIN, 30));
		playerName.setBackground(primaryColor);
		playerName.setBounds(0, 0, 469, 38);
		doc = playerName.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(playerName);
		
		playerInfo1 = new JTextArea();
		playerInfo1.setEditable(false);
		playerInfo1.setForeground(primaryColor);
		playerInfo1.setLineWrap(true);
		playerInfo1.setFont(new Font("Courier New", Font.PLAIN, 14));
		playerInfo1.setBackground(secondaryColor);
		playerInfo1.setBounds(0, 38, 232, 63);
		add(playerInfo1);
		
		playerInfo2 = new JTextArea();
		playerInfo2.setEditable(false);
		playerInfo2.setForeground(primaryColor);
		playerInfo2.setLineWrap(true);
		playerInfo2.setFont(new Font("Courier New", Font.PLAIN, 14));
		playerInfo2.setBackground(secondaryColor);
		playerInfo2.setBounds(231, 38, 238, 63);
		add(playerInfo2);
		
		careerInfo = new JTextPane();
		careerInfo.setEditable(false);
		careerInfo.setForeground(secondaryColor);
		careerInfo.setFont(new Font("Courier New", Font.BOLD, 12));
		careerInfo.setBackground(primaryColor);
		careerInfo.setBounds(11, 112, 144, 59);
		doc = careerInfo.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(careerInfo);
		
		salaryInfo = new JTextPane();
		salaryInfo.setEditable(false);
		salaryInfo.setForeground(secondaryColor);
		salaryInfo.setFont(new Font("Courier New", Font.PLAIN, 12));
		salaryInfo.setBackground(primaryColor);
		salaryInfo.setBounds(162, 112, 144, 59);
		doc = salaryInfo.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(salaryInfo);
		
		houseInfo = new JTextPane();
		houseInfo.setEditable(false);
		houseInfo.setForeground(secondaryColor);
		houseInfo.setFont(new Font("Courier New", Font.PLAIN, 12));
		houseInfo.setBackground(primaryColor);
		houseInfo.setBounds(313, 112, 144, 59);
		doc = houseInfo.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(houseInfo);
		
	}
	
	public void updateUserInfo (Player player){
		String newLine = "\n";
		String gap = "           ";
		playerName.setText(player.getName());
		
		playerInfo1.setText("BALANCE  : " + player.getBalance() + newLine
				          + "MARRIED  : " + player.isMarried() + newLine
				          + "BABIBES  : " + player.getBabies());
		
		playerInfo2.setText("DEBT     : " + player.getDebt() + newLine
				          + "RETIRED  : " + player.isFinish() + newLine
				          + "DEGREE   : " + player.getDegree());
		
		
		Career job = player.getJob();
		
		if(job != NULL) {
			careerInfo.setText("CAREER" + newLine +  job.getPosition() + newLine
							 + "RAISES" + newLine +  job.getCtr() + " / " + job.getMax());
			
			salaryInfo.setText("SALARY" + newLine  + job.getSalary() + newLine
					         + "TAX" + newLine + job.getTax());
		}
		else {
			careerInfo.setText("CAREER" + newLine +  "N/A" + newLine
					 + "RAISES" + newLine + "N/A");
	
			salaryInfo.setText("SALARY" + newLine  + "N/A" + newLine
			         + "TAX" + newLine + "N/A");
}
		House h = player.getHouse();
		
		if(h != null) {
			houseInfo.setText("HOUSE" + newLine + h.getName() + newLine
							+ "VALUE" + newLine + h.getValue());
		}
		
		else {
			houseInfo.setText("HOUSE" + newLine + "N/A" + newLine
					+ "VALUE" + newLine + "N/A");
		}
		
	}
}
