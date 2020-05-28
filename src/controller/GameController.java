package controller;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Vector;


import model.*;
import view.*;

public class GameController implements Controller{
	
	private Game model;//אבא של שלושת סוגי המשחקים
	private MainScreen mainView=new MainScreen(); //מסך שבוחרים הגדרות
	private GeneralGameBuilder gameScreenView; //מסך משחק עצמו מטריצה קלפים 
	
	public GameController(Game model,MainScreen view)
	{
		this.mainView=view;
		this.model=model;	
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof MainScreen)
		{
			MainScreen.GameSettings gs=mainView.new GameSettings();
			gs=(MainScreen.GameSettings)arg;
			if(gs.getGameType()==0)
			{
			model=new AgainstRival(gs.getPlayer1(),gs.getPlayer2(),gs.getDifficulty());	
			}
			else if(gs.getGameType()==1)
			{
			model=new AgainstTime(gs.getPlayer1(),gs.getDifficulty());	
			}
			else if(gs.getGameType()==2)
			{
			model=new AgainstComputer(gs.getPlayer1(),gs.getDifficulty());	
			}
		}
		else if(o instanceof Game) 
		{
			 if(arg instanceof AgainstTime.TimeGameSettings) 
			 {
				 AgainstTime at=new AgainstTime();
				 AgainstTime.TimeGameSettings timeGS=at.new TimeGameSettings();
				 timeGS=(AgainstTime.TimeGameSettings)arg;//קסטינג
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(timeGS.getP1Name()).setDifficulty(timeGS.getDifficulty()).build();
			 }
			 else if(arg instanceof AgainstComputer.CompGameSettings) 
			 {
				 AgainstComputer ac=new AgainstComputer();
				 AgainstComputer.CompGameSettings compGS=ac.new CompGameSettings();
				 compGS=(AgainstComputer.CompGameSettings)arg;//קסטינג
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(compGS.getP1Name()).SecondPlayersName(compGS.getP2Name()).setDifficulty(compGS.getDifficulty()).build();
			 }
			 else if(arg instanceof AgainstRival.RivalGameSettings) 
			 {
				 AgainstRival ar=new AgainstRival();
				 AgainstRival.RivalGameSettings rivalGS=ar.new RivalGameSettings();
				 rivalGS=(AgainstRival.RivalGameSettings)arg;//קסטינג
				 GeneralGameBuilder gameScreenView = new GeneralGameBuilder.Builder().FirstPlayersName(rivalGS.getP1Name()).SecondPlayersName(rivalGS.getP2Name()).setDifficulty(rivalGS.getDifficulty()).build();
			 }
			 else if(arg instanceof AgainstRival.RivalWhosTurn)
			 {
				 
			 }
		
		
		}
	}	

}
