package model;

import javax.swing.ImageIcon;

public class AgainstRival extends Game{
	private int playersTurn;
	
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
		
//Difficulty level affects only numOfCards
		if(difficulty==0)
		{
			numOfCards=12;
		}
		else if(difficulty==1)
		{
			numOfCards=16;
		}
		else 
		{
			numOfCards=24;
		}
		
		photoIndex = new int[numOfCards];
		nRandomIntegers(numOfCards);
		photoFound = new boolean[numOfCards];
	}
	public int whosTurn() {return playersTurn;}
}
