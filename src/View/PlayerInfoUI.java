package View;
import javax.swing.*;

import Model.SalaryCard.SalaryCard;
import Model.Player.Career;
import Model.Player.Player;
import java.awt.Color;
import java.awt.Font;

public class PlayerInfoUI extends JPanel{
	
	private static final Career NULL = null;
	
	private Color primaryColor;
	private Color secondaryColor;
	
	private JTextArea playerInfo1;
	private JTextArea playerInfo2;
	private JTextArea playerName;
	private JTextArea careerInfo;
	private JTextArea salaryInfo;
	private JTextArea houseInfo;
	
	public PlayerInfoUI (Color pc, Color sc) {
		setBackground(new Color(255, 255, 255));
		primaryColor = pc;
		secondaryColor = sc;
		
		init ();
		
		setLayout(null);
	}
	
	public void init () {
		
		playerName = new JTextArea();
		playerName.setEditable(false);
		playerName.setForeground(secondaryColor);
		playerName.setFont(new Font("Courier New", Font.PLAIN, 30));
		playerName.setBackground(primaryColor);
		playerName.setBounds(0, 0, 462, 38);
		add(playerName);
		
		playerInfo1 = new JTextArea();
		playerInfo1.setEditable(false);
		playerInfo1.setForeground(primaryColor);
		playerInfo1.setLineWrap(true);
		playerInfo1.setFont(new Font("Courier New", Font.PLAIN, 14));
		playerInfo1.setBackground(secondaryColor);
		playerInfo1.setBounds(0, 38, 230, 63);
		add(playerInfo1);
		
		playerInfo2 = new JTextArea();
		playerInfo2.setEditable(false);
		playerInfo2.setForeground(primaryColor);
		playerInfo2.setLineWrap(true);
		playerInfo2.setFont(new Font("Courier New", Font.PLAIN, 14));
		playerInfo2.setBackground(secondaryColor);
		playerInfo2.setBounds(230, 38, 232, 63);
		add(playerInfo2);
		
		careerInfo = new JTextArea();
		careerInfo.setEditable(false);
		careerInfo.setForeground(new Color(255, 255, 255));
		careerInfo.setFont(new Font("Courier New", Font.BOLD, 12));
		careerInfo.setBackground(new Color(183, 166, 173));
		careerInfo.setBounds(0, 101, 154, 59);
		add(careerInfo);
		
		salaryInfo = new JTextArea();
		salaryInfo.setEditable(false);
		salaryInfo.setForeground(new Color(255, 255, 255));
		salaryInfo.setFont(new Font("Courier New", Font.PLAIN, 12));
		salaryInfo.setBackground(new Color(190, 178, 167));
		salaryInfo.setBounds(154, 101, 154, 59);
		add(salaryInfo);
		
		houseInfo = new JTextArea();
		houseInfo.setEditable(false);
		houseInfo.setForeground(new Color(255, 255, 255));
		houseInfo.setFont(new Font("Courier New", Font.PLAIN, 12));
		houseInfo.setBackground(new Color(168, 173, 180));
		houseInfo.setBounds(308, 101, 154, 59);
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
				          + "POSITION : " + player.getPosition());
		
		
		Career job = player.getJob();
		
		if(job != NULL) {
			careerInfo.setText("CAREER" + newLine +  job.getPosition() + newLine
							 + "RAISES  : " + job.getCtr() + " / " + job.getMax());
			
			salaryInfo.setText("SALARY" + newLine  + job.getSalary() + newLine
					         + "TAX     : " + job.getTax());
		}
		else {
			careerInfo.setText("CAREER" + newLine +  "N/A" + newLine
					 + "RAISES  : " + "N/A");
	
			salaryInfo.setText("SALARY" + newLine  + "N/A" + newLine
			         + "TAX     : " + "N/A");
}
		
		
	}
}
