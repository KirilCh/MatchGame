package model;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class AgainstTime extends Game{
	private int countDown;
	public class TimeGameSettings
	{
		String gameLevel;
		String player1;
		public TimeGameSettings(){}
		public TimeGameSettings(String level,String p1) 
		{
			gameLevel=level;
			player1=p1;
		}
		public String getDifficulty() {return gameLevel;}
		public String getP1Name() {return player1;}

	}
	
	private TimeGameSettings gameSettings;
	public AgainstTime() {}
	public AgainstTime(String p1,int difficulty) 
	{
		players=new String[1];
		players[0]=p1;
		score = new int[2];//Initialized with 0`s
		choiceNumber=1; 
		
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
		if (difficulty==0) level="Easy";
		else if (difficulty==1) level="Medium";
		else level="Hard";
		gameSettings=new TimeGameSettings(level,p1);
		numOfCards=24;

//Difficulty level affects only game time length
		if(difficulty==0)
		{
			countDown=15;
		}
		else if(difficulty==1)
		{
			countDown=10;
		}
		else 
		{
			countDown=5;
		}
		
		photoIndex = new int[numOfCards];
		nRandomIntegers(numOfCards);
		photoFound = new boolean[numOfCards];
	}
	public int getCountDown() {return countDown;}
}
