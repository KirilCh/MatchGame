package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Vector;

public class MainScreen{

	private JFrame frame;
	private final ButtonGroup buttonGroup_kind = new ButtonGroup();
	private final ButtonGroup buttonGroup_number = new ButtonGroup();
	private final ButtonGroup buttonGroup_level = new ButtonGroup();
	private JPanel panel;
	private JLabel titleL,secondPNameL,pNames,firstPNameL,pNum,gKind,dLevelL;
	private JRadioButton twoPlayersRadioButton,onePlayerRadioButton,againstTimeRadioButton,againstCompRadioButton,medLevel,hardLevel
	,easLevel,oneOnOne;	
	private JButton adminButton,startGame;
	private JComboBox<String> p2List;
	private JComboBox<String> p1List;
	private static Boolean oneP=false,twoP=false;
	//private final JLayeredPane againstCompMode = new JLayeredPane();
	 ChildManagementScreen childScreen;
	//private JLabel errorL;
	private JButton childrenManagementButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
		/*try {
			this.frame.setVisible(true);
		
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//gameName title
		titleL = new JLabel("\u05D6\u05DB\u05D5\u05E8 \u05D0\u05EA \u05D4\u05E4\u05D5\u05E2\u05DC");
		titleL.setForeground(new Color(255, 0, 0));
		titleL.setHorizontalAlignment(SwingConstants.RIGHT);
		titleL.setFont(new Font("Guttman Kav-Light", Font.BOLD, 34));
		titleL.setBounds(252, 42, 252, 39);
		panel.add(titleL);

		//2 players radioB
		twoPlayersRadioButton = new JRadioButton("2");
		twoPlayersRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		twoPlayersRadioButton.setHorizontalTextPosition(JRadioButton.LEADING);
		twoPlayersRadioButton.setHorizontalAlignment(JRadioButton.TRAILING);
		twoPlayersRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twoPlayersRadioButtonActionPerformed(e);
			}
		});
		buttonGroup_number.add(twoPlayersRadioButton);
		twoPlayersRadioButton.setBounds(690, 156, 59, 25);
		panel.add(twoPlayersRadioButton);
		
		//1 player radioB
		onePlayerRadioButton = new JRadioButton("1");
		onePlayerRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		onePlayerRadioButton.setHorizontalTextPosition(JRadioButton.LEADING);
		onePlayerRadioButton.setHorizontalAlignment(JRadioButton.TRAILING);
		onePlayerRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onePlayerRadioButtonActionPerformed(e);
			}
		});
		buttonGroup_number.add(onePlayerRadioButton);
		onePlayerRadioButton.setBounds(690, 126, 59, 25);
		panel.add(onePlayerRadioButton);
		
		//number Title
		pNum = new JLabel("\u05DE\u05E1\u05E4\u05E8 \u05E9\u05D7\u05E7\u05E0\u05D9\u05DD:");
		pNum.setHorizontalAlignment(SwingConstants.RIGHT);
		pNum.setFont(new Font("Tahoma", Font.BOLD, 17));
		pNum.setBounds(614, 104, 135, 21);
		panel.add(pNum);
		
		//admin Button
		adminButton = new JButton("\u05E1\u05D5\u05E4\u05E8-\u05DE\u05E9\u05EA\u05DE\u05E9");
		adminButton.setFont(new Font("Guttman Kav-Light", Font.PLAIN, 20));
		adminButton.setBounds(12, 13, 149, 25);
		panel.add(adminButton);
		
		//kind Title
		gKind = new JLabel("\u05E1\u05D5\u05D2 \u05DE\u05E9\u05D7\u05E7:");
		gKind.setHorizontalAlignment(SwingConstants.RIGHT);
		gKind.setFont(new Font("Tahoma", Font.BOLD, 17));
		gKind.setBounds(614, 190, 135, 21);
		gKind.setEnabled(false);
		panel.add(gKind);
		
		//againstTime Mode
		againstTimeRadioButton = new JRadioButton("\u05DE\u05E9\u05D7\u05E7 \u05E0\u05D2\u05D3 \u05D4\u05D6\u05DE\u05DF");
		againstTimeRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		againstTimeRadioButton.setHorizontalTextPosition(JRadioButton.LEADING);
		againstTimeRadioButton.setHorizontalAlignment(JRadioButton.TRAILING);
		againstTimeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnyKindButtonActionPerformed(e);
			}
		});
		buttonGroup_kind.add(againstTimeRadioButton);
		againstTimeRadioButton.setBounds(587, 214, 162, 25);
		againstTimeRadioButton.setEnabled(false);
		panel.add(againstTimeRadioButton);

		//againstComp Mode		
		againstCompRadioButton = new JRadioButton("\u05DE\u05E9\u05D7\u05E7 \u05E0\u05D2\u05D3 \u05D4\u05DE\u05D7\u05E9\u05D1");
		againstCompRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup_kind.add(againstCompRadioButton);
		againstCompRadioButton.setHorizontalTextPosition(JRadioButton.LEADING);
		againstCompRadioButton.setHorizontalAlignment(JRadioButton.TRAILING);
		againstCompRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnyKindButtonActionPerformed(e);
			}
		});
		againstCompRadioButton.setBounds(570, 244, 179, 25);
		againstCompRadioButton.setEnabled(false);
		panel.add(againstCompRadioButton);
		
		//name Title
		pNames = new JLabel("\u05E9\u05DE\u05D5\u05EA \u05D4\u05E9\u05D7\u05E7\u05E0\u05D9\u05DD:");
		pNames.setHorizontalAlignment(SwingConstants.RIGHT);
		pNames.setFont(new Font("Tahoma", Font.BOLD, 17));
		pNames.setBounds(597, 422, 149, 21);
		pNames.setEnabled(false);
		panel.add(pNames);
		
		//p1 nameLabel		
		firstPNameL = new JLabel("\u05E9\u05D7\u05E7\u05DF 1:");
		firstPNameL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstPNameL.setHorizontalAlignment(SwingConstants.RIGHT);
		firstPNameL.setBounds(676, 453, 70, 25);
		firstPNameL.setEnabled(false);
		panel.add(firstPNameL);
		
		//p2 nameLabel
		secondPNameL = new JLabel("\u05E9\u05D7\u05E7\u05DF 2:");
		secondPNameL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		secondPNameL.setHorizontalAlignment(SwingConstants.RIGHT);
		secondPNameL.setBounds(676, 487, 70, 25);
		secondPNameL.setEnabled(false);
		panel.add(secondPNameL);
		
		//level Title
		dLevelL = new JLabel("\u05E8\u05DE\u05EA \u05D4\u05E7\u05D5\u05E9\u05D9:");
		dLevelL.setHorizontalAlignment(SwingConstants.RIGHT);
		dLevelL.setFont(new Font("Tahoma", Font.BOLD, 17));
		dLevelL.setBounds(626, 308, 123, 21);
		dLevelL.setEnabled(false);
		panel.add(dLevelL);
		
		//med level radioB
		medLevel = new JRadioButton("\u05D1\u05D9\u05E0\u05D5\u05E0\u05D9");
		medLevel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup_level.add(medLevel);
		medLevel.setHorizontalTextPosition(JRadioButton.LEADING);
		medLevel.setHorizontalAlignment(JRadioButton.TRAILING);
		medLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnylevelButtonActionPerformed(e);
			}
		});
		medLevel.setBounds(667, 361, 82, 25);
		medLevel.setEnabled(false);
		panel.add(medLevel);
		
		//easy level radioB
		easLevel = new JRadioButton("\u05E7\u05DC");
		easLevel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup_level.add(easLevel);
		easLevel.setHorizontalTextPosition(JRadioButton.LEADING);
		easLevel.setHorizontalAlignment(JRadioButton.TRAILING);
		easLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnylevelButtonActionPerformed(e);
			}
		});
		easLevel.setBounds(693, 331, 56, 25);
		easLevel.setEnabled(false);
		panel.add(easLevel);
		
		//hard level radioB
		hardLevel = new JRadioButton("\u05E7\u05E9\u05D4");
		hardLevel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup_level.add(hardLevel);
		hardLevel.setHorizontalTextPosition(JRadioButton.LEADING);
		hardLevel.setHorizontalAlignment(JRadioButton.TRAILING);
		hardLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnylevelButtonActionPerformed(e);
			}
		});
		hardLevel.setBounds(679, 388, 70, 25);
		hardLevel.setEnabled(false);
		panel.add(hardLevel);
		
		//one-on-one radioB
		oneOnOne = new JRadioButton("\u05D0\u05D7\u05D3 \u05E2\u05DC \u05D0\u05D7\u05D3");
		oneOnOne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		oneOnOne.setHorizontalTextPosition(JRadioButton.LEADING);
		oneOnOne.setHorizontalAlignment(JRadioButton.TRAILING);
		buttonGroup_kind.add(oneOnOne);
		oneOnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnyKindButtonActionPerformed(e);
			}
		});
		oneOnOne.setBounds(597, 274, 152, 25);
		oneOnOne.setEnabled(false);
		panel.add(oneOnOne);

		//start-game button
		startGame = new JButton("\u05D4\u05EA\u05D7\u05DC \u05DE\u05E9\u05D7\u05E7!");
		startGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onStartGameButtonActionPerformed(e);
			}
		});
		startGame.setFont(new Font("Guttman Kav-Light", Font.PLAIN, 24));
		startGame.setForeground(new Color(255, 0, 0));
		startGame.setBackground(new Color(0, 206, 209));
		startGame.setBounds(31, 467, 162, 43);
		startGame.setEnabled(false);
		panel.add(startGame);
	
	/*	againstCompMode.setVisible(false);
		againstCompMode.setBackground(new Color(255, 0, 0));
		againstCompMode.setBounds(0, 0, 769, 596);
		panel.add(againstCompMode);
		
		errorL = new JLabel("*\u05D0\u05D7\u05D3 \u05D0\u05D5 \u05D9\u05D5\u05EA\u05E8 \u05DE\u05D4\u05E0\u05EA\u05D5\u05E0\u05D9\u05DD \u05E9\u05D2\u05D5\u05D9");
		errorL.setForeground(new Color(255, 0, 0));
		errorL.setHorizontalAlignment(SwingConstants.RIGHT);
		errorL.setBounds(22, 434, 172, 30);
		errorL.setVisible(false);
		againstCompMode.add(errorL);*/
		
		childrenManagementButton = new JButton("\u05E0\u05D4\u05DC \u05D0\u05EA \u05E8\u05E9\u05D9\u05DE\u05EA \u05D4\u05D9\u05DC\u05D3\u05D9\u05DD");
	
		childrenManagementButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openScreen(e);
			}
		});
		childrenManagementButton.setFont(new Font("Guttman Kav-Light", Font.PLAIN, 20));
		childrenManagementButton.setBounds(12, 52, 213, 25);
		panel.add(childrenManagementButton);
		
		//TESTS ON COMBO BOX
		/*list=new Data();
		list.addChild(new Children("����","205649000"));
	    list.addChild(new Children("����","34534636"));
	    list.addChild(new Children("���","23423536"));
		*/
		
		p1List = new JComboBox<>(new Vector());
		p1List.setBounds(554, 456, 113, 30);
		p1List.setEnabled(false);
		panel.add(p1List);
		
		p2List = new JComboBox<>(new Vector());
		p2List.setBounds(554, 490, 113, 30);
		p2List.setEnabled(false);
		panel.add(p2List);
	}

	protected void openScreen(MouseEvent e) {
		childScreen=new ChildManagementScreen();
		childScreen.display();
	}

	protected void onStartGameButtonActionPerformed(MouseEvent e) {
		if(startGame.isEnabled()==true)
		{
			/*if(oneP&&firstNameT.getText().length()>=2)
			{
				errorL.setVisible(false);
				frame.setVisible(false);
				if(againstTimeRadioButton.isSelected())
				{
					tMode=new GameInProgress(firstNameT.getText(),2);
					tMode.setVisible(true);
				}
				else if(againstCompRadioButton.isSelected())
				{
					cMode=new AgainstComputer(24,firstNameT.getText());
					cMode.setVisible(true);
				}
				//else if() - 2 players option
			    }
			else
				errorL.setVisible(true);*/
		}
	}

	protected void onAnylevelButtonActionPerformed(ActionEvent e) {
		startGame.setEnabled(true);
		pNames.setEnabled(true);
		firstPNameL.setEnabled(true);
		p1List.setEnabled(true);
		if(twoP==true) {
			
			secondPNameL.setEnabled(true);
			p2List.setEnabled(true);	
		}
		else 
			{
			p2List.setEnabled(false);
			secondPNameL.setEnabled(false);
		}
	}

	protected void onAnyKindButtonActionPerformed(ActionEvent e) {
		dLevelL.setEnabled(true);
		easLevel.setEnabled(true);
		medLevel.setEnabled(true);
		hardLevel.setEnabled(true);
	}

	protected void twoPlayersRadioButtonActionPerformed(ActionEvent e) {
		if(gKind.isEnabled())
		{
			againstTimeRadioButton.setEnabled(false);
			againstCompRadioButton.setEnabled(false);
			oneP=false;
			oneOnOne.setSelected(true);
		}
		else
		{
			gKind.setEnabled(true);
		}
		oneOnOne.setEnabled(true);
		twoP=true;
		if(pNames.isEnabled())
			onAnylevelButtonActionPerformed(e);
	}

	protected void onePlayerRadioButtonActionPerformed(ActionEvent e) {
		if(gKind.isEnabled())
		{
			oneOnOne.setEnabled(false);
			twoP=false;
			againstTimeRadioButton.setSelected(true);
		}
		else
		{
			gKind.setEnabled(true);	
		}
		againstTimeRadioButton.setEnabled(true);
		againstCompRadioButton.setEnabled(true);
		oneP=true;
		if(pNames.isEnabled())
			onAnylevelButtonActionPerformed(e);
	}
}
