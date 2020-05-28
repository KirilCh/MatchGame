package model;

import javax.swing.ImageIcon;


public class AgainstRival extends Game{
	private int playersTurn;
	public class RivalWhosTurn
	{
		int playersTurn;
		public int getWhosTurn() {return playersTurn;}
	}
	public class RivalGameSettings
	{
		String gameLevel;
		String player1;
		String player2;
		public RivalGameSettings(){}
		public RivalGameSettings(String level,String p1,String p2) 
		{
			gameLevel=level;
			player1=p1;
			player2=p2;

		}
		public String getDifficulty() {return gameLevel;}
		public String getP1Name() {return player1;}
		public String getP2Name() {return player2;}
	}
	private RivalGameSettings gameSettings;
	private RivalWhosTurn whosTurn;
	public AgainstRival() {} 

	public AgainstRival(String p1,String p2,int difficulty) 
	{
		players=new String[2];
		players[0]=p1;
		players[1]=p2;
		score = new int[4];//Initialized with 0`s
		choiceNumber=1;
		playersTurn=1;
		
		imagePhoto = new ImageIcon[12]; //An array of images used to play the game
		
		imagePhoto[0] = new ImageIcon("PhotoName1.jpg");
		imagePhoto[1] = new ImageIcon("PhotoName2.jpg");
		imagePhoto[2] = new ImageIcon("PhotoName3.jpg");
		imagePhoto[3] = new ImageIcon("PhotoName4.jpg");
		imagePhoto[4] = new ImageIcon("PhotoName5.jpg");
		imagePhoto[5] = new ImageIcon("PhotoName6.jpg");
		imagePhoto[6] = new ImageIcon("PhotoName7.jpg");
		imagePhoto[7] = new ImageIcon("PhotoName8.jpg");
		imagePhoto[8] = new ImageIcon("PhotoName9.jpg");
		imagePhoto[9] = new ImageIcon("PhotoName10.jpg");
		imagePhoto[10] = new ImageIcon("PhotoName11.jpg");
		imagePhoto[11] = new ImageIcon("PhotoName12.jpg");
		
		imageCover = new ImageIcon("coverPhotoName.jpg");
		
		String level;
		//Difficulty level affects only numOfCards
		if(difficulty==0)
		{
			level="Easy";
			numOfCards=12;
		}
		else if(difficulty==1)
		{
			level="Medium";
			numOfCards=16;
		}
		else 
		{
			level="Hard";
			numOfCards=24;
		}
		gameSettings=new RivalGameSettings(level,p1,p2);
		photoIndex = new int[numOfCards];
		nRandomIntegers(numOfCards);
		photoFound = new boolean[numOfCards];
		whosTurn=new RivalWhosTurn();
		whosTurn.playersTurn=1;
	}
	public RivalWhosTurn whosTurn() {return whosTurn;}
}
