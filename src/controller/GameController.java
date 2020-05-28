package controller;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.ImageIcon;
import model.*;
import view.*;

public class GameController implements Controller{
	
	private Game model;//
	private AgainstComputer computerModel;
	private MainScreen mainView; //
	private GeneralGameBuilder gameScreenView; // 
	
	public GameController(MainScreen view,GeneralGameBuilder gameScreenView,AgainstComputer compM)
	{
		this.mainView=view;
		//this.model=model;
		this.gameScreenView=gameScreenView;
		computerModel=compM;
	}
	@Override
	public void update(Observable o, Object arg) 
	{	
		//
		if(o instanceof MainScreen)
		{
			MainScreen.GameSettings gs=mainView.new GameSettings();
			gs=(MainScreen.GameSettings)arg;
			if(gs.getGameType()==0)
			{
			model=new AgainstRival(gs.getPlayer1(),gs.getPlayer2(),gs.getDifficulty());	
			model.addObserver(this);

			}
			else if(gs.getGameType()==1)
			{
			model=new AgainstTime(gs.getPlayer1(),gs.getDifficulty());	
			model.addObserver(this);

			}
			else if(gs.getGameType()==2)
			{
			model=new AgainstComputer(gs.getPlayer1(),gs.getDifficulty());	
			model.addObserver(this);
			}
		}
		//
		else if(o instanceof Game) //MODEL TO VIEW 
		{
			 if(arg instanceof AgainstTime.TimeGameSettings) 
			 {
				 AgainstTime at=new AgainstTime();
				 AgainstTime.TimeGameSettings timeGS=at.new TimeGameSettings();
				 timeGS=(AgainstTime.TimeGameSettings)arg;//
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(timeGS.getP1Name()).setDifficulty(timeGS.getDifficulty()).setGameLength(timeGS.getTime()).build();
			 }
			 else if(arg instanceof AgainstComputer.CompGameSettings) 
			 {
				 AgainstComputer ac=new AgainstComputer();
				 AgainstComputer.CompGameSettings compGS=ac.new CompGameSettings();
				 compGS=(AgainstComputer.CompGameSettings)arg;//
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(compGS.getP1Name()).SecondPlayersName(compGS.getP2Name()).setDifficulty(compGS.getDifficulty()).build();
			 }
			 else if(arg instanceof AgainstRival.RivalGameSettings) 
			 {
				 AgainstRival ar=new AgainstRival();
				 AgainstRival.RivalGameSettings rivalGS=ar.new RivalGameSettings();
				 rivalGS=(AgainstRival.RivalGameSettings)arg;//
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(rivalGS.getP1Name()).SecondPlayersName(rivalGS.getP2Name()).setDifficulty(rivalGS.getDifficulty()).build();
			 }
		     // 
		     else if(arg instanceof Boolean)
	      	{
			 gameScreenView.checkMatchResult((Boolean)arg);
	     	}// checkMatchResult -UI
			 
	     	//
	    	else if(arg instanceof ImageIcon) 
	    	{
			gameScreenView.getImageCover((ImageIcon)arg);
	     	}
		   //
		    if(arg instanceof ImageIcon[]) 
		    {
			gameScreenView.getPhotosArray((ImageIcon[])arg);
		    }
		   // getPhotosArray-UI
		   //
	    	else if(arg instanceof int[]) 
		    {
			gameScreenView.getCompMove((int[])arg);
	     	}
		   /*? 
	    	else if(arg instanceof AgainstRival.RivalWhosTurn) 
		    {
			AgainstRival ar=new AgainstRival();
			AgainstRival.RivalWhosTurn rivalWS=ar.new RivalWhosTurn();
			rivalWS=(AgainstRival.RivalWhosTurn)arg;
			gameScreenView.whosTurnAnswer(rivalWS.getWhosTurn());
		    }
		    */
		 /*  else if(arg instanceof AgainstComputer.CompWhosTurn)
		    {
			 AgainstComputer ac=new AgainstComputer();
		     AgainstComputer.CompWhosTurn compWS=ac.new CompWhosTurn();
		     compWS=(AgainstComputer.CompWhosTurn)arg;
		     gameScreenView.whosTurnAnswer(compWS.getWhosTurn());
		    }*/
		   //
		   else if(arg instanceof Game.ScoreCalc)
		    {
			   Game.ScoreCalc scoreC=model.new ScoreCalc();
			   scoreC=(Game.ScoreCalc)arg;
			   gameScreenView.scoreCalcAnswer(scoreC.getScore());
		    } 
		    //model to view index arr
			else if (arg instanceof Game.PhotoIndex)
			    {
        			 
        			Game.PhotoIndex pi= model.new PhotoIndex();
    				pi=(Game.PhotoIndex)arg;//
    				gameScreenView.getPhotoIndex(pi.getPhotoIndex());
    			 }
			else if(arg instanceof Boolean[])
	    	{
	    		gameScreenView.getPhotoFound((boolean[]) arg);
	    	}
		    
		if(o instanceof GeneralGameBuilder) //VIEW TO MODEL
		    {
			//
			 if(arg instanceof GeneralGameBuilder.CheckMatch)//
			{
				 GeneralGameBuilder ggb=new GeneralGameBuilder();
				 GeneralGameBuilder.CheckMatch checkingM=ggb.new CheckMatch();
				 checkingM=( GeneralGameBuilder.CheckMatch)arg;//
				 model.checkMatch(checkingM.getFirstIndex(),checkingM.getSecondIndex(), checkingM.getWhosTurn());// ARG
			}
			 //
			else if (arg instanceof GeneralGameBuilder.GetImageCover)//
			{
				model.getImagecover();		
			}
			 //
			else if (arg instanceof GeneralGameBuilder.GetPhotosArray)//  
			{
				model.getImagePhotoArr();		
			}
			 //
			else if(arg instanceof GeneralGameBuilder.CompTurn)//
			{
				computerModel.compTurn();
			}	
			 //
			/*else if(arg instanceof GeneralGameBuilder.CompWhosTurn)//
			{
				computerModel.whosTurn();
			} 
			else if(arg instanceof GeneralGameBuilder.RivalWhosTurn)
			{
				model.whosTurn();
			}*/
			else if(arg instanceof GeneralGameBuilder.GetScoreCalc)//
			{
				model.scoreCalc((int)arg);
			}
			 //
			else if (arg instanceof GeneralGameBuilder.GetPhotoIndex)
			{
	             model.getPhotoIndex();
			}
	        	 else if(arg instanceof GeneralGameBuilder.GetPhotoFound)
	 			{
	 				model.getPhotoFound();
	 			}
		
		}
	}
	/*
	*/
	
	
	
}
}