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
		int time;
		public TimeGameSettings(){}
		public TimeGameSettings(String level,String p1,int time) 
		{
			gameLevel=level;
			player1=p1;
			this.time=time;
		}
		public String getDifficulty() {return gameLevel;}
		public String getP1Name() {return player1;}
		public int getTime() {return time;}

	}
	
	private TimeGameSettings gameSettings;
	public AgainstTime() {}
	public AgainstTime(String p1,int difficulty) 
	{
		players=new String[1];
		players[0]=p1;
		score = new int[2];//Initialized with 0`s
		//choiceNumber=1; 
		photoIndexInner=new PhotoIndex();
		imagePhoto = new ImageIcon[12]; //An array of images used to play the game
		
		imagePhoto[0] = new ImageIcon("PhotoName1.jpeg");
		imagePhoto[1] = new ImageIcon("PhotoName2.jpeg");
		imagePhoto[2] = new ImageIcon("PhotoName3.jpeg");
		imagePhoto[3] = new ImageIcon("PhotoName4.jpeg");
		imagePhoto[4] = new ImageIcon("PhotoName5.jpeg");
		imagePhoto[5] = new ImageIcon("PhotoName6.jpeg");
		imagePhoto[6] = new ImageIcon("PhotoName7.jpeg");
		imagePhoto[7] = new ImageIcon("PhotoName8.jpeg");
		imagePhoto[8] = new ImageIcon("PhotoName9.jpeg");
		imagePhoto[9] = new ImageIcon("PhotoName10.jpeg");
		imagePhoto[10] = new ImageIcon("PhotoName11.jpeg");
		imagePhoto[11] = new ImageIcon("PhotoName12.jpeg");
		
		imageCover = new ImageIcon("cover.jpg");
		String level;
		if (difficulty==0) level="Easy";
		else if (difficulty==1) level="Medium";
		else level="Hard";
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
		gameSettings=new TimeGameSettings(level,p1,countDown);

		photoIndex = new int[numOfCards];
		nRandomIntegers(numOfCards);
		photoFound = new boolean[numOfCards];
		photoIndexInner.setPhotoIndex(photoIndex);
	}
}
