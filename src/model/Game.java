package model;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Game implements CardsGame{
	protected ImageIcon[] imagePhoto;
	protected ImageIcon imageCover; //the cover photo that will display on all the closed cards
	protected int[] photoIndex;//each cell represent image index- initialize with 2n cards
	protected boolean[] photoFound;//give indication about the cards - if found in a match already
	protected int photoRemaining;//amount of pictures that left unmatched
	protected int choiceNumber;//number of selected cards in the same turn (1 or 2)
	protected int[] score;//index 0 score of player 1, index1 combo of player 1, same goes for player 2
	protected int numOfCards;
	protected String[] players;//maintain the players name
	
	public String[] getPlayersNames() {return players;}
	public int getRemainingPhotoNum(boolean []photoFound)
	{
		//for(int i=0;i<photoFound.length;i++)
			//if(photoFound[i])
				//photoRemaining++;
		return photoRemaining;
	}
	
	public Boolean checkMatch(int firstI,int secondI,int whosTurn) 
	{
		if(photoIndex[firstI]==photoIndex[secondI])
		{
			//scoreCalc() //UI
			if(whosTurn==1) //Combo increment
				score[1]++;
			else score[3]++;
			photoRemaining--;
			photoFound[firstI]=true;
			photoFound[secondI]=true;
			return true;
		}
		else 
		{
			if(whosTurn==1) //Combo reset
				score[1]=0;
			else score[3]=0;
			return false;
		}
	}
	
	public void nRandomIntegers(int n) 
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
		for(int i=0;i<n;i++)
			if(nIntegers[i]>(n/2)-1)
				nIntegers[i]-=(n/2);
		photoIndex=nIntegers;
	}
	public ImageIcon[] getImagePhotoArr() {return imagePhoto;}
	public ImageIcon getImagecover() {return imageCover;}
	public int scoreCalc(int whosTurn) 
	{
		if(whosTurn==1)
		{
			score[0]+= 5*(score[1]);
			return score[0];
		}
		else
		{
			score[2]+=5*(score[3]);
			return score[2];
		}
	}
	public void setChoiceNumber()
	{
		if(choiceNumber==1)
			choiceNumber=2;
		else choiceNumber=1;
	}
	public int getNumOfCards()
	{
		return numOfCards;
	}
	
}
