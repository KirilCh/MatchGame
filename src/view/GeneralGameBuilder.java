package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
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
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.applet.*;

public class GeneralGameBuilder extends Observable implements View//extends JFrame
{
	public class GameDetails {
			
		int score;
		String gametype;
		String name;
		public int getScore() {
			return score;
		}
		public String getGametype() {
			return gametype;
		}
		public String getName() {
			return name;
		}
	}
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
	protected ImageIcon cover;
	protected ImageIcon[] photos;
	protected int[] photosIndex;
	protected int[] choice = new int[2]; //Keep players selection
	protected int choiceNumber;
	protected int whosTurn=1;
	protected int photosRemaining;
	protected boolean[] whichPhotosFound;
	protected boolean is2Players;
	
	AudioClip matchSound;
	AudioClip noMatchSound;
	AudioClip gameOverSound;
	
	protected int labelSelected;
	protected Timer displayTimer;
	protected int delay=1000; //600 ms delay between card flips
	protected boolean isClickable=true;
	//protected Timer timer = null; //Timer variable
	
	public class GetImageCover{}
	public class GetPhotosArray{}
	public class GetPhotoIndex{}
	public class CompTurn{}
	public class CompWhosTurn{}
	public class RivalWhosTurn{}
	public class GetScoreCalc
	{
		private int whosTurn;
		public GetScoreCalc() {}
		public GetScoreCalc(int playersTurn) {whosTurn=playersTurn;}
		public int getWhosTurn() {return whosTurn;}
	}
	
	public class CheckMatch
	{
		private int firstIndex;
		private int secondIndex;
		private int whosTurn;
		
		public CheckMatch() {}
		public CheckMatch(int first,int second,int whoPlays) {firstIndex=first;secondIndex=second;whosTurn=whoPlays;}
		public int getFirstIndex() {return firstIndex;}
		public int getSecondIndex() {return secondIndex;}
		public int getWhosTurn() {return whosTurn;}
	}
	
	public class GetPhotoFound{}
	public GeneralGameBuilder() {}
	
	public GeneralGameBuilder(Builder bld)
	{
		cover=new ImageIcon(bld.cover.getDescription());
		
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
			is2Players=true;
		}
		else
		{
			is2Players=false;
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
			photoLabel[i].setIcon(bld.cover);
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
			});
		}
		
	
		
		photosRemaining=bld.numOfCards/2;
		//Get photos for the game
		/*setChanged();
		notifyObservers(new GetPhotosArray());
		
		//Get photos indexes for the game // photosIndex
		setChanged();
		notifyObservers(new GetPhotoIndex());
		*/
		buttonsPanel = new JPanel();
		startStopButton = new JButton();
		exitButton = new JButton();
		
		buttonsPanel.setPreferredSize(new Dimension(200,70));
		buttonsPanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		mainWindow.getContentPane().add(buttonsPanel, gridConstraints);
		
		/*startStopButton.setText("Stop Game");
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
				});*/
		
		exitButton.setText("Exit");
		exitButton.setEnabled(true); //Disabled as long as game in progress
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
		choiceNumber=1;//Initializing choiceNumber to 1 to indicate first selection of the card
		if(isAgainstComputer==true)
		{
			mainWindow.setTitle("Current game = AgainstComputer...");
		}
		
		try
		{
			matchSound = Applet.newAudioClip(new URL("file:" + "tada.wav"));
			noMatchSound = Applet.newAudioClip(new URL("file:" + "card_flip2.wav"));
			gameOverSound = Applet.newAudioClip(new URL("file:" + "wow.wav"));
		}
		catch (Exception ex)
		{
			System.out.println("Error loading sound files");
		}
		whichPhotosFound = new boolean[bld.numOfCards];
		photos=new ImageIcon[12];
		for(int i=0;i<12;i++)
		{
			photos[i]=bld.photos[i];
		}
		photosIndex=new int[bld.numOfCards];
		for(int i=0;i<bld.numOfCards;i++)
		{
			photosIndex[i]=bld.photosIndex[i];
		}
		
		
		displayTimer = new Timer(delay, new ActionListener()
				{
			public void actionPerformed(ActionEvent evt)
			{
				displayTimerActionPerformed(evt);
			}
				});
		mainWindow.setVisible(true);
	}
	
	
	private void photoLabelMousePressed(MouseEvent e)
	{
		Point p = e.getComponent().getLocation();
		
		if(isClickable==true) //Check if allowed to click to avoid illegal card flips
		{
			for(labelSelected=0;labelSelected<photoLabel.length;labelSelected++)
			{
				if(p.x==photoLabel[labelSelected].getX() && p.y==photoLabel[labelSelected].getY())
				{
					break;
				}
			}
		setChanged();
		notifyObservers(new GetPhotoFound());
		}
	}
	
	private void displayTimerActionPerformed(ActionEvent evt)
	{
		displayTimer.stop();
		
		
		if(isClickable==false)
			isClickable=true;
		if(isAgainstComputer==true && whosTurn==2)
		{
			choiceNumber=1;
			
			setChanged();
			notifyObservers(new CheckMatch(choice[0],choice[1],whosTurn));
		}
		else
		{
			if(choiceNumber==1)
			{
				choice[0]=labelSelected;
				choiceNumber=2;
				messageLabel.setText(player1Label.getText() + " pick a card"); //Updating players pick
			
				//Check if 1 or 2 players game, and act accordingly
			}
			else
			{
				choice[1]=labelSelected;
				choiceNumber=1;
			
				setChanged();
				notifyObservers(new CheckMatch(choice[0],choice[1],whosTurn));
			}
		}
	}
	/*public void getImageCover(ImageIcon imgCover) {
		cover=new ImageIcon();
		cover=imgCover;
		//cover.getIconWidth();
		} //Card cover*/
	/*public void getPhotosArray(ImageIcon[] photosArr) {photos=photosArr;} //Card for the game
	public void getPhotoIndex(int[] photoIndex) {photosIndex=photoIndex;} //Connecting labels to cards*/
	
	public void getCompMove(int[] compChoice)//Array is of size 2, containing 1st and 2nd pick
	{
		//Reveal the 2 cards the computer selected
		this.photoLabel[compChoice[0]].setIcon(this.photos[photosIndex[compChoice[0]]]);
		
		
		//Set a delay before next turn
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.photoLabel[compChoice[1]].setIcon(this.photos[photosIndex[compChoice[1]]]);
		choice[0]=compChoice[0];
		choice[1]=compChoice[1];
		
		displayTimer.start();
	}
	
//	public void whosTurnAnswer() {}
	public void getPhotoFound(boolean[] photoFound)
	{
		//whichPhotosFound = photoFound;
		if(!photoFound[labelSelected])
		{
			showSelectedLabel();
		}
	}
	public void scoreCalcAnswer(int score) 
	{
		if(whosTurn==1)
		{
			scoreTextField[0].setText(Integer.toString(score));
		}
		else
		{
			scoreTextField[1].setText(Integer.toString(score));
		}
	}
	public void checkMatchResult(boolean result)
	{
		if(result==true) //Its a match
		{
			matchSound.play();
			photoLabel[choice[0]].setBackground(null);
			photoLabel[choice[0]].setIcon(null);
			photoLabel[choice[1]].setBackground(null);
			photoLabel[choice[1]].setIcon(null);
			
			//Update score - updated in checkMatch, need to update display
			setChanged();
			notifyObservers(new GetScoreCalc(whosTurn));
			
			photosRemaining--;
			if(whosTurn==1)
			{
				messageLabel.setText(player1Label.getText() + " pick a card"); //Updating players pick
			}
			else
			{
				messageLabel.setText(player2Label.getText() + " pick a card"); //Updating players pick
			}
			
			if(photosRemaining==0)
			{
				if(is2Players==true && isAgainstComputer==false)
				{
					//AgainstRival
				}
				else if(is2Players==true && isAgainstComputer==true)
				{
					//AgainstComputer
					System.exit(0);
				}
				else
				{
					//AgainstTime
				}
				
				//End game, Thanos won
				//Check with Orel about end game page
				//Which parameteres to pass to constructor
				//Split into 2 constructors - 1 for 1 player, and another 2 players
			}
			if(isAgainstComputer==true && whosTurn==2)
			{
				//Set a delay before next turn
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				setChanged();
				notifyObservers(new CompTurn());
			}
		}
		else
		{
			//In case theres no match, return the cards to be covered
			noMatchSound.play();
			photoLabel[choice[0]].setIcon(cover);
			photoLabel[choice[1]].setIcon(cover);
			//Change players turn
			if(is2Players==true)
			{
				if(whosTurn==1 && isAgainstComputer==false)
				{
					whosTurn=2;
					messageLabel.setText(player2Label.getText() + " pick another"); //Updating players pick
				}
				else if(whosTurn==1 && isAgainstComputer==true)
				{
					whosTurn=2;
					
					//Set a delay before next turn
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					setChanged();
					notifyObservers(new CompTurn());
				}
				else 
				{
					whosTurn=1;
					messageLabel.setText(player1Label.getText() + " pick another"); //Updating players pick
				}
			}
		}
	}
	private void letCompPlay()
	{
		
	}
	private void showSelectedLabel()
	{
		if(isClickable==true)
		{
			this.photoLabel[labelSelected].setIcon(this.photos[photosIndex[labelSelected]]);
			isClickable=false;
			displayTimer.start();
		}
		
		
		//photoFound[labelSelected] = true -> Changed when theres a match
	}
	public static class Builder
	{
		private String p1Name;
		private String p2Name;
		private String difficulty;
		private int gameLength;
		private boolean is2Players=false;
		private int numOfCards;
		private ImageIcon cover;
		private int[] photosIndex;
		private ImageIcon[] photos;
		
		
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
			//	this.gameLength=15;
			}
			else if(this.difficulty=="Medium")
			{
				this.numOfCards=16;
			//	this.gameLength=10;
			}
			else if(this.difficulty=="Hard")
			{
				this.numOfCards=24;
			//	this.gameLength=5;
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
		public Builder setGameLength(int num)
		{
			this.gameLength=num;
			return this;
		}
		
		public Builder setImageCover(ImageIcon cover)
		{
			this.cover=new ImageIcon();
			this.cover=cover;
			return this;
		}
		public Builder setPhotosIndex(int[] photosIndex)
		{
			this.photosIndex=new int[this.numOfCards];
			this.photosIndex=photosIndex;
			return this;
		}
		public Builder setPhotos(ImageIcon[] photos)
		{
			this.photos = new ImageIcon[12];
			this.photos=photos;
			return this;
		}
	}
}
