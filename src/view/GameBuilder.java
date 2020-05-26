package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class GameBuilder extends JFrame implements View
{
	public static void main(String args[])
	{
		//Create main frame window
		new GameBuilder().show();
	}
	int[] photoIndex;
	JLabel player1Label = new JLabel();
	JLabel player2Label = new JLabel();
	public GameBuilder()
	{
		//Frame constructor
		setTitle("Match Game");
		setResizable(true); //The ability to resize window while running
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt)
			{
				exitForm(evt);
			}
		});
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;
		pack();
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds((int) (0.5*(screenSize.width-getWidth())), (int) (0.5*(screenSize.height - getHeight())), 1678,859);
		setBounds(400,100,1200,800); //To set up the game window in the center of the screen when running
		
		
		//Photos for the game + randomizing the array of photos
		photoIndex = new int[20];	
		photoIndex = nRandomIntegers(20);
	/*	for(int i=0;i<20;i++)
			if(photoIndex[i]>9)
				photoIndex[i]-=10;*/
		
		int photosRemaining; //How many photos are still hidden
		int[] score = new int[2]; //score[0] for player1, score[1] for player2
		boolean[] photoFound = new boolean[20]; //An array to follow up on matched photos and to try and match a new pair
		int playerNumber, choiceNumber; //playerNumber - whos turn is it, choiceNumber - players 1st or 2nd choice
		int[] choice = new int[2]; //holds indexes of the two picture boxes selected
		boolean canClick = false; //used to determine if user allowed to click on picture box controls. When playing = true, when game is finished = false
		boolean gameOver; //Is the game finished (all photos matched) = true, or StopGame was clicked before finishing the game = false
		
		//Controls and Labels for cards
		JPanel gamePanel = new JPanel();
		JLabel[] photoLabel = new JLabel[20];
		
		gamePanel.setPreferredSize(new Dimension(625, 530));
		gamePanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
		gridConstraints.insets = new Insets(0, 0, 0, 5);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridheight = 5;
		getContentPane().add(gamePanel, gridConstraints);
		
		for (int i = 0; i < 20; i++)
		{
			photoLabel[i] = new JLabel();
			photoLabel[i].setPreferredSize(new Dimension(150, 100));
			photoLabel[i].setOpaque(true);
			photoLabel[i].setBackground(Color.CYAN);
			
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = i % 4;
			gridConstraints.gridy = i / 4;
			gridConstraints.insets = new Insets(5, 5, 0, 0);
			
			if (gridConstraints.gridx == 3)
				gridConstraints.insets = new Insets(5, 5, 0, 5);
			
			if (gridConstraints.gridy == 4)
				gridConstraints.insets = new Insets(5, 5, 5, 0);
			
			if (gridConstraints.gridx == 3 && gridConstraints.gridy == 4)
				gridConstraints.insets = new Insets(5, 5, 5, 5);
		
			gamePanel.add(photoLabel[i], gridConstraints);
		
			photoLabel[i].addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					photoLabelMousePressed(e);
				}
			});}

		//Controls and Labels for player name and score, message label
		JPanel resultsPanel = new JPanel();
		
		JTextField[] scoreTextField = new JTextField[2];
		JLabel messageLabel = new JLabel();
		
		resultsPanel.setPreferredSize(new Dimension(160,140));
		resultsPanel.setLayout(new GridBagLayout());
		
		//gridConstraints = new GridBagConstraints();
		GridBagConstraints gridConstraintsL; //Transfer later to beginning of the code block
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.insets = new Insets(0, 0, 5, 0);
		gridConstraintsL.gridx = 1;
		gridConstraintsL.gridy = 0;
		getContentPane().add(resultsPanel, gridConstraintsL);
		
		//Player 1 INFO
		player1Label.setText("Player 1");
		player1Label.setFont(new Font("Arial",Font.BOLD,16));
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.gridx=0;
		gridConstraintsL.gridy=0;
		resultsPanel.add(player1Label,gridConstraintsL);
		
		scoreTextField[0] = new JTextField();
		scoreTextField[0].setPreferredSize(new Dimension(100,25));
		scoreTextField[0].setText("0");
		scoreTextField[0].setEditable(false);
		scoreTextField[0].setBackground(Color.WHITE);
		scoreTextField[0].setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextField[0].setFont(new Font("Aria",Font.PLAIN,16));
		
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.gridx = 0;
		gridConstraintsL.gridy = 1;
		resultsPanel.add(scoreTextField[0],gridConstraintsL);
		
		//PLayer 2 INFO
		player2Label.setText("Player 2");
		player2Label.setFont(new Font("Arial",Font.BOLD,16));
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.gridx = 0;
		gridConstraintsL.gridy = 2;
		gridConstraintsL.insets = new Insets(5,0,0,0);
		resultsPanel.add(player2Label,gridConstraintsL);
		
		scoreTextField[1] = new JTextField();
		scoreTextField[1].setPreferredSize(new Dimension(100,25));
		scoreTextField[1].setText("0");
		scoreTextField[1].setEditable(false);
		scoreTextField[1].setBackground(Color.WHITE);
		scoreTextField[1].setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextField[1].setFont(new Font("Arial",Font.PLAIN,16));
		
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.gridx = 0;
		gridConstraintsL.gridy = 3;
		resultsPanel.add(scoreTextField[1],gridConstraintsL);
		
		messageLabel.setPreferredSize(new Dimension(160,40));
		messageLabel.setOpaque(true);
		messageLabel.setBackground(Color.YELLOW);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setText("");
		messageLabel.setFont(new Font("Arial",Font.PLAIN, 14));
		
		gridConstraintsL = new GridBagConstraints();
		gridConstraintsL.gridx = 0;
		gridConstraintsL.gridy = 4;
		gridConstraintsL.insets = new Insets(5,0,0,0);
		resultsPanel.add(messageLabel,gridConstraintsL);
		
		
		//Who plays panel
		JPanel whoPlaysPanel = new JPanel();
		ButtonGroup whoPlaysButtonGroup = new ButtonGroup();
		JRadioButton playAloneRadioButton = new JRadioButton();
		JRadioButton playComputerRadioButton = new JRadioButton();
		
		JPanel difficultyPanel = new JPanel();
		ButtonGroup difficultyButtonGroup = new ButtonGroup();
		JRadioButton easiestRadioButton = new JRadioButton();
		JRadioButton easyRadioButton = new JRadioButton();
		JRadioButton hardRadioButton = new JRadioButton();
		JRadioButton hardestRadioButton = new JRadioButton();
		
		whoPlaysPanel.setPreferredSize(new Dimension(160,75));
		whoPlaysPanel.setBackground(Color.green);
		whoPlaysPanel.setBorder(BorderFactory.createTitledBorder("Who plays?"));
		whoPlaysPanel.setLayout(new GridBagLayout());
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		gridConstraints.insets = new Insets(5,10,5,10);
		getContentPane().add(whoPlaysPanel, gridConstraints);
		
		playAloneRadioButton.setText("Play alone");
		playAloneRadioButton.setBackground(Color.GREEN);
		playAloneRadioButton.setSelected(true);
		
		whoPlaysButtonGroup.add(playAloneRadioButton);
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.WEST;
		
		whoPlaysPanel.add(playAloneRadioButton, gridConstraints);
		
		playAloneRadioButton.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent evt)
				{
			playAloneRadioButtonActionPerformed(evt,/*player2Label*/ easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
				}
				});
		
		
		playComputerRadioButton.setText("Play computer");
		playComputerRadioButton.setBackground(Color.GREEN);
		whoPlaysButtonGroup.add(playComputerRadioButton);
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.anchor = GridBagConstraints.WEST;
		
		whoPlaysPanel.add(playComputerRadioButton, gridConstraints);
		
		playComputerRadioButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				playComputerRadioButtonActionPerformed(evt, player2Label, easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
			}
				});
		
		
		//==========================
		//Play against computer difficulties level
				
				
				difficultyPanel.setPreferredSize(new Dimension(160,125));
				difficultyPanel.setBackground(Color.GREEN);
				difficultyPanel.setBorder(BorderFactory.createTitledBorder("Select difficulty level"));
				difficultyPanel.setLayout(new GridBagLayout());
				
				gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = 1;
				gridConstraints.gridy = 3;
				gridConstraints.insets = new Insets(5,10,5,10);
				getContentPane().add(difficultyPanel, gridConstraints);
				
				easiestRadioButton.setText("Easiest");
				easiestRadioButton.setBackground(Color.GREEN);
				easiestRadioButton.setSelected(true);
				difficultyButtonGroup.add(easiestRadioButton);
				
				gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = 0;
				gridConstraints.gridy = 0;
				gridConstraints.anchor = GridBagConstraints.WEST;
				
				difficultyPanel.add(easiestRadioButton, gridConstraints);
				
				easiestRadioButton.addActionListener(new ActionListener()
						{
					public void actionPerformed(ActionEvent evt)
					{
						easiestRadioButtonActionPerformed(evt);
					}
						});
				
				easyRadioButton.setText("Easy");
				easyRadioButton.setBackground(Color.GREEN);
				easyRadioButton.setSelected(false);
				difficultyButtonGroup.add(easyRadioButton);
				
				gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = 0;
				gridConstraints.gridy = 1;
				gridConstraints.anchor = GridBagConstraints.WEST;
				
				difficultyPanel.add(easyRadioButton, gridConstraints);
				
				easyRadioButton.addActionListener(new ActionListener() 
						{
					public void actionPerformed(ActionEvent evt)
					{
						easyRadioButtonActionPerformed(evt);
					}
						});
				
				hardRadioButton.setText("Hard");
				hardRadioButton.setBackground(Color.GREEN);
				hardRadioButton.setSelected(false);
				difficultyButtonGroup.add(hardRadioButton);
				
				gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = 0;
				gridConstraints.gridy = 2;
				gridConstraints.anchor = GridBagConstraints.WEST;
				
				difficultyPanel.add(hardRadioButton, gridConstraints);
				
				hardRadioButton.addActionListener(new ActionListener() 
						{
					public void actionPerformed(ActionEvent evt)
					{
						hardRadioButtonActionPerformed(evt);
					}
						});
				
				hardestRadioButton.setText("Hardest");
				hardestRadioButton.setBackground(Color.GREEN);
				hardestRadioButton.setSelected(false);
				difficultyButtonGroup.add(hardestRadioButton);
				
				gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = 0;
				gridConstraints.gridy = 3;
				gridConstraints.anchor = GridBagConstraints.WEST;
				
				difficultyPanel.add(hardestRadioButton, gridConstraints);
				
				hardestRadioButton.addActionListener(new ActionListener() 
						{
					public void actionPerformed(ActionEvent evt)
					{
						hardestRadioButtonActionPerformed(evt);
					}
						});
		
		//No. of players selection controls
		JPanel playersPanel = new JPanel();
		ButtonGroup playersButtonGroup = new ButtonGroup();
		JRadioButton onePlayerRadioButton = new JRadioButton();
		JRadioButton twoPlayersRadioButton = new JRadioButton();
		
		
		playersPanel.setPreferredSize(new Dimension(160,75));
		playersPanel.setBackground(Color.GREEN);
		playersPanel.setBorder(BorderFactory.createTitledBorder("Number of players?"));
		playersPanel.setLayout(new GridBagLayout());
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.insets = new Insets(5,10,5,10);
		getContentPane().add(playersPanel,gridConstraints);
		
		twoPlayersRadioButton.setText("Two Players");
		twoPlayersRadioButton.setBackground(Color.GREEN);
		twoPlayersRadioButton.setSelected(true); //Setting default selection
		
		playersButtonGroup.add(twoPlayersRadioButton); //Adding first radio button to panel
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.WEST;
		
		playersPanel.add(twoPlayersRadioButton, gridConstraints);
		
		twoPlayersRadioButton.addActionListener(new ActionListener() 
				{
			public void actionPerformed(ActionEvent evt)
			{
				twoPlayersRadioButtonActionPerformed(evt,playAloneRadioButton,playComputerRadioButton,easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
			}
				});
		
		onePlayerRadioButton.setText("One Player");
		onePlayerRadioButton.setBackground(Color.GREEN);
		playersButtonGroup.add(onePlayerRadioButton); //Adding second radio button to panel
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.anchor = GridBagConstraints.WEST;
		
		playersPanel.add(onePlayerRadioButton, gridConstraints);
		
		onePlayerRadioButton.addActionListener(new ActionListener() 
		{
	public void actionPerformed(ActionEvent evt)
	{
		onePlayerRadioButtonActionPerformed(evt, onePlayerRadioButton, twoPlayersRadioButton,player1Label,playAloneRadioButton,player2Label,easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton,playComputerRadioButton);
	}
		});
		
	
		//Game controls - Start/Stop and Exit buttons
		JPanel buttonsPanel = new JPanel();
		JButton startStopButton = new JButton();
		JButton exitButton = new JButton();
		
		buttonsPanel.setPreferredSize(new Dimension(160,70));
		buttonsPanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		getContentPane().add(buttonsPanel, gridConstraints);
		
		startStopButton.setText("Start Game");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		buttonsPanel.add(startStopButton, gridConstraints);
		
		startStopButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				startStopButtonActionPerformed(evt);
			}
				});
		
		exitButton.setText("Exit");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.insets = new Insets (10,0,0,0);
		buttonsPanel.add(exitButton, gridConstraints);
		
		exitButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				exitButtonActionPerformed(evt);
			}
				});
		
		
		//====================
		
		//Initializing stopped state
		
		ImageIcon[] photo = new ImageIcon[10]; //An array of images used to play the game
		ImageIcon cover = new ImageIcon(); //Image used as the cards cover
		
		photo[0] = new ImageIcon("above the clouds.jpg");
		photo[1] = new ImageIcon("black white walk.jpg");
		photo[2] = new ImageIcon("heli jump.jpg");
		photo[3] = new ImageIcon("japow.jpg");
		photo[4] = new ImageIcon("Mountain.jpg");
		photo[5] = new ImageIcon("old town jump.jpg");
		photo[6] = new ImageIcon("peak.jpg");
		photo[7] = new ImageIcon("snow lagoon.jpg");
		photo[8] = new ImageIcon("snow splash.jpg");
		photo[9] = new ImageIcon("sunset.jpg");
		
		cover = new ImageIcon("cover.jpg");
		
		for(int i = 0; i<20; i++)
			photoLabel[i].setIcon(cover);
		
		/*for(int i=0;i<20;i++)
			photoLabel[i].setIcon(photo[photoIndex[i]]);*/
		
		//How do we know what photo is behind what picture box control?
		//photoLabel[i].setIcon(photo[photoIndex[i]]);
		
		setPlayWhoButtons(false, playAloneRadioButton, playComputerRadioButton);
		setDifficultyButtons(false, easiestRadioButton, easyRadioButton, hardRadioButton, hardestRadioButton);
		
		
	}
	
	private void exitForm(WindowEvent evt) //Exit button action
	{
		System.exit(0);;
	}
	private void photoLabelMousePressed(MouseEvent evt) //Mouse click action on PhotoLabel items
	{
		
	}
	private void twoPlayersRadioButtonActionPerformed(ActionEvent evt, Component playAloneRadioButton, Component playComputerRadioButton, Component easiestRadioButton, Component easyRadioButton, Component hardRadioButton, Component hardestRadioButton)
	{
		setPlayWhoButtons(false, playAloneRadioButton, playComputerRadioButton);
		setDifficultyButtons(false, easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
	}
	private void onePlayerRadioButtonActionPerformed(ActionEvent evt, Component onePlayerRadioButton, Component twoPlayersRadioButton, JLabel player1Label, JRadioButton playAloneRadioButton,JLabel player2Label, Component easiestRadioButton, Component easyRadioButton, Component hardRadioButton, Component hardestRadioButton, Component playComputerRadioButton)
	{
		setPlayWhoButtons(true, playAloneRadioButton, playComputerRadioButton);
		player1Label.setText("You");
		
		if(playAloneRadioButton.isSelected())
		{
			player2Label.setText("Guesses");
			setDifficultyButtons(false, easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
		}
		else
		{
			player2Label.setText("Computer");
			setDifficultyButtons(true, easiestRadioButton, easyRadioButton, hardRadioButton, hardestRadioButton);
		}
	}
	private void playAloneRadioButtonActionPerformed(ActionEvent evt, /*JLabel player2Label*/ Component easiestRadioButton, Component easyRadioButton, Component hardRadioButton, Component hardestRadioButton)
	{
		setDifficultyButtons(false, easiestRadioButton,easyRadioButton,hardRadioButton,hardestRadioButton);
		player2Label.setText("Guesses");
	}
	private void playComputerRadioButtonActionPerformed(ActionEvent evt, JLabel player2Label, Component easiestRadioButton, Component easyRadioButton, Component hardRadioButton, Component hardestRadioButton)
	{
		setDifficultyButtons(true, easiestRadioButton, easyRadioButton, hardRadioButton, hardestRadioButton);
		player2Label.setText("Computer");
	}
	private void easiestRadioButtonActionPerformed(ActionEvent evt)
	{
		
	}
	private void easyRadioButtonActionPerformed(ActionEvent evt)
	{
		
	}
	private void hardRadioButtonActionPerformed(ActionEvent evt)
	{
		
	}
	private void hardestRadioButtonActionPerformed(ActionEvent evt)
	{
		
	}
	private void startStopButtonActionPerformed(ActionEvent evt)
	{
		
	}
	private void exitButtonActionPerformed(ActionEvent evt)
	{
		System.exit(0);
	}
	private void setPlayersButton(boolean a, Component onePlayerRadioButton, Component twoPlayersRadioButton)
	{
		onePlayerRadioButton.setEnabled(a);
		twoPlayersRadioButton.setEnabled(a);
	}
	private void setPlayWhoButtons(boolean a, Component playAloneRadioButton, Component playComputerRadioButton)
	{
		playAloneRadioButton.setEnabled(a);
		playComputerRadioButton.setEnabled(a);
	}
	private void setDifficultyButtons(boolean a, Component easiestRadioButton, Component easyRadioButton, Component hardRadioButton, Component hardestRadioButton)
	{
		easiestRadioButton.setEnabled(a);
		easyRadioButton.setEnabled(a);
		hardRadioButton.setEnabled(a);
		hardestRadioButton.setEnabled(a);
	}
	private int[] nRandomIntegers(int n) //Shuffle cards method
	{

		int[] nIntegers = new int[n];
		int temp, s;
		Random sortRandom = new Random(); //Need to import "java.util.Random"
		
		//Init array from 0 to n-1
		for(int i = 0;i<n;i++)
		{
			nIntegers[i]=i;
		}
		
		//I is the number of items remaining in the list
		for(int i=n;i>=1;i--)
		{
			s = sortRandom.nextInt(i);
			temp = nIntegers[s];
			nIntegers[s] = nIntegers[i-1];
			nIntegers[i-1] = temp;
		}
		for(int i=0;i<20;i++)
			if(nIntegers[i]>9)
				nIntegers[i]-=10;
		return nIntegers;
	}
	
}


