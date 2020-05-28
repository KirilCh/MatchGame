package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.util.Observable;


public class GeneralGameBuilder extends Observable implements View//extends JFrame
{
	JFrame mainWindow = new JFrame();
	
	
	protected JPanel contentPanel;
	protected JPanel gamePanel;
	protected JPanel resultsPanel;
	protected JLabel[] photoLabel;
	protected JTextField[] scoreTextField; //An array to keep the score of 1 or 2 players
	protected JLabel messageLabel; //Used to separate block in results panel bloc
	protected JLabel player1Label; //Player 1 name
	protected JLabel player2Label; //Player 2 name
	protected JPanel buttonsPanel; //Right lower panel for start/stop and exit buttons
	protected JPanel timerPanel; //Time countdown panel
	protected JButton startStopButton;
	protected JButton exitButton;
	protected boolean isAgainstComputer=false;
	//protected Timer timer = null; //Timer variable
	
	public class GetImageCover{}
	public class GetPhotosArray{}
	public class CompTurn{}
	public class CompWhosTurn{}
	public class RivalWhosTurn{}
	public class GetScoreCalc{}
	
	public class CheckMatch
	{
		public void getFirstIndex() {}
		public void getSecondIndex() {}
		public void getWhosTurn() {}
	}
	private GeneralGameBuilder() {}
	
	private GeneralGameBuilder(Builder bld)
	{
		mainWindow.getContentPane().setLayout(new GridBagLayout());
		//getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;
		mainWindow.pack();
		
		mainWindow.setTitle("Game in progress..."); //Check later how to place it in the center of the screen
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setBounds(400,100,1100,800);//Setting up app window size and placing in the center of my screen
	
		gamePanel = new JPanel();
		
		
		gamePanel.setPreferredSize(new Dimension(625, 530));
		gamePanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
		gridConstraints.insets = new Insets(0, 0, 0, 5);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridheight = 5;
		mainWindow.getContentPane().add(gamePanel, gridConstraints);
		
		
		
		
		//Players score and names area
		player1Label = new JLabel();
		resultsPanel = new JPanel();
		scoreTextField = new JTextField[2];
		messageLabel = new JLabel();
		
		resultsPanel.setPreferredSize(new Dimension(160,140));
		resultsPanel.setLayout(new GridBagLayout());
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.insets = new Insets(0, 0, 5, 0);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		mainWindow.getContentPane().add(resultsPanel, gridConstraints);
		
		//Player1 INFO
		//player1Label.setText(bld.player1Label.getText());
		player1Label.setText(bld.p1Name);
		player1Label.setFont(new Font("Arial",Font.BOLD,16));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=0;
		resultsPanel.add(player1Label,gridConstraints);
		
		scoreTextField[0] = new JTextField();
		scoreTextField[0].setPreferredSize(new Dimension(100,25));
		scoreTextField[0].setText("0");
		scoreTextField[0].setEditable(false);
		scoreTextField[0].setBackground(Color.WHITE);
		scoreTextField[0].setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextField[0].setFont(new Font("Aria",Font.PLAIN,16));
		
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		resultsPanel.add(scoreTextField[0],gridConstraints);
		
		//Player2 INFO
		if(bld.is2Players==true)
		{
			player2Label = new JLabel();
			player2Label.setText(bld.p2Name);
			player2Label.setFont(new Font("Arial",Font.BOLD,16));
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = 0;
			gridConstraints.gridy = 2;
			gridConstraints.insets = new Insets(5,0,0,0);
			resultsPanel.add(player2Label,gridConstraints);
			
			//player2Label.setVisible(true);
					
			scoreTextField[1] = new JTextField();
			scoreTextField[1].setPreferredSize(new Dimension(100,25));
			scoreTextField[1].setText("0");
			scoreTextField[1].setEditable(false);
			scoreTextField[1].setBackground(Color.WHITE);
			scoreTextField[1].setHorizontalAlignment(SwingConstants.CENTER);
			scoreTextField[1].setFont(new Font("Arial",Font.PLAIN,16));
			//scoreTextField[1].setVisible(false);
					
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = 0;
			gridConstraints.gridy = 3;
			resultsPanel.add(scoreTextField[1],gridConstraints);
			
			if(bld.p2Name=="computer")
			{
				isAgainstComputer=true;
			}
		}
		else
		{
			final long countDownTimer=bld.gameLength*1000*60;
			bld.numOfCards=24;
			
			timerPanel = new JPanel();
			timerPanel.setPreferredSize(new Dimension(160,140));
			//timerPanel.setBackground(Color.WHITE);
			timerPanel.setBorder(BorderFactory.createTitledBorder("Time left"));
			timerPanel.setLayout(new GridBagLayout());
			gridConstraints = new GridBagConstraints();
			gridConstraints.insets = new Insets(10,0,0,0);
			gridConstraints.gridx=1;
			gridConstraints.gridy=3;
			mainWindow.getContentPane().add(timerPanel,gridConstraints);
			
			//countDownTimer = gameLength*1000*60; //timer variable is miliseconds units -> converting integer of "minutes" to miliseconds
			final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
			final JLabel clockDown = new JLabel(sdf.format(new Date(countDownTimer)), new ImageIcon("ClockIcon.png"),JLabel.CENTER);
			clockDown.setFont(new Font("Arial",Font.BOLD,20));
			
			//int tempTimerVar = 0;
			ActionListener al;
			al = new ActionListener() {
				long tempTimerVar = countDownTimer - 1000;
				public void actionPerformed(ActionEvent evt)
				{
					clockDown.setText(sdf.format(new Date(tempTimerVar)));
					if(tempTimerVar==0)
					{
						JOptionPane.showMessageDialog(null, "Time is up!", "Game Over", JOptionPane.WARNING_MESSAGE); // Pop up message
						//clockDown.setText("00:00");
						 ((Timer)evt.getSource()).stop(); //Stops the timer
						 
						 //Add stop game functions later here
					}
					else tempTimerVar-=1000;
				}
			};
			new javax.swing.Timer(1000,  al).start();
			
			timerPanel.add(clockDown);
		}
		
		messageLabel.setPreferredSize(new Dimension(160,40));
		messageLabel.setOpaque(true);
		messageLabel.setBackground(Color.YELLOW);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setText("");
		messageLabel.setFont(new Font("Arial",Font.PLAIN, 14));
				
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 10;
		gridConstraints.insets = new Insets(5,0,0,0);
		resultsPanel.add(messageLabel,gridConstraints);
		
		
		//Setting up cards as labels on the window
		photoLabel = new JLabel[bld.numOfCards];
		
		for (int i = 0; i < bld.numOfCards; i++)
		{
			photoLabel[i] = new JLabel();
			photoLabel[i].setPreferredSize(new Dimension(120, 75));//150,100
			photoLabel[i].setOpaque(true);
			photoLabel[i].setBackground(Color.DARK_GRAY);
			
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
					//photoLabelMousePressed(e);
				}
			});
		}
		
		buttonsPanel = new JPanel();
		startStopButton = new JButton();
		exitButton = new JButton();
		
		buttonsPanel.setPreferredSize(new Dimension(200,70));
		buttonsPanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		mainWindow.getContentPane().add(buttonsPanel, gridConstraints);
		
		startStopButton.setText("Stop Game");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		buttonsPanel.add(startStopButton, gridConstraints);
		
		startStopButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				//startStopButtonActionPerformed(evt);
			}
				});
		
		exitButton.setText("Exit");
		exitButton.setEnabled(false); //Disabled as long as game in progress
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.insets = new Insets (10,0,0,0);
		buttonsPanel.add(exitButton, gridConstraints);
		
		exitButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				//exitButtonActionPerformed(evt);
			}
				});
		
		if(isAgainstComputer==true)
		{
			mainWindow.setTitle("Current game = AgainstComputer...");
		}
		mainWindow.setVisible(true);
	}
	
	public void getImageCover(ImageIcon imgCover) {}
	public void getPhotosArray(ImageIcon[] photosArr) {}
	public void getCompMove(int[] arr){}
	
	public void whosTurnAnswer() {}
	public void scoreCalcAnswer() {}
	
	
	public static class Builder
	{
		private String p1Name;
		private String p2Name;
		private String difficulty;
		private int gameLength;
		private boolean is2Players=false;
		private int numOfCards;
		
		public GeneralGameBuilder build()
		{
			return new GeneralGameBuilder(this);
		}
		public Builder setDifficulty(String difficulty)
		{
			this.difficulty=difficulty;
			if(this.difficulty=="Easy")
			{
				this.numOfCards=12;
				this.gameLength=15;
			}
			else if(this.difficulty=="Medium")
			{
				this.numOfCards=16;
				this.gameLength=10;
			}
			else if(this.difficulty=="Hard")
			{
				this.numOfCards=24;
				this.gameLength=5;
			}
			else 
			{
				this.numOfCards=0;
				this.gameLength=0;
			}
			return this;
		}
		public Builder FirstPlayersName(String name)
		{
			this.p1Name = name;
			return this;
		}
		public Builder SecondPlayersName(String name)
		{
			this.is2Players=true;
			this.p2Name = name;
			return this;
		}
	}
}
