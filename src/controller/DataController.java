package controller;
import javax.swing.*;

import org.jfree.chart.JFreeChart;

import model.Children;
import model.Data;
import model.GameRecord;
import view.ChildManagementScreen;
import view.GeneralGameBuilder;
import view.MainScreen;
import view.StatisticsScreen;
import view.View;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class DataController implements Controller{
	
	private Data data;
	private MainScreen Ms;
	private GeneralGameBuilder Gs;
	private StatisticsScreen Ss;
	private ChildManagementScreen Cms;
	private View view;
	
	public DataController(Data d,MainScreen ms,GeneralGameBuilder gs,StatisticsScreen ss,ChildManagementScreen cms)
	{
		this.data=d;
		this.Ms=ms;
		this.Gs=gs;
		this.Ss=ss;
		this.Cms=cms;
		//data.addObserver(this);
		//Ms.addObserver(this);
		//Ss.addObserver(this);
		//Cms.addObserver(this);
		//Gs.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof Data)
		{
			data=(Data)o;
			if(view instanceof MainScreen)
			{
				Ms.updateList((Vector) arg);
			}
			if(view instanceof ChildManagementScreen)
			{
				Cms.updateList((Vector) arg);
			}
			if(view instanceof StatisticsScreen)
			{
				Ss.updateList((Vector) arg);
			}
		    if(arg instanceof JFreeChart)
			{
		    	Ss.Showgraph((JFreeChart) arg);
			}
			if(arg instanceof JTable)
			{
				Ss.Showtable((JTable) arg);
			}
			
		}
		else if(o instanceof MainScreen)
		{
			Ms=(MainScreen)o;
			view=new MainScreen();
			data.getChildrenList();
			if(arg instanceof MainScreen.ExitEvent)
			{
			
				data.closeFile();
			}
		}
		else if(o instanceof StatisticsScreen)
		{
			Ss=(StatisticsScreen)o;
			if(arg instanceof StatisticsScreen.UpdateList)//make inner class
			{
				view=new StatisticsScreen();
				data.getChildrenList();
			}
			else if(arg instanceof StatisticsScreen.LinearStats)//make inner class
			{
				StatisticsScreen.LinearStats graph=Ss.new LinearStats();
				graph=(StatisticsScreen.LinearStats)arg;
				data.MakeLinearStatsPerChild(graph.getIndex());
			}
			else if(arg instanceof StatisticsScreen.TableStats)//make inner class
			{
				data.makeGeneralScoreStats();
			}
		}
		else if(o instanceof ChildManagementScreen)
		{
			Cms=(ChildManagementScreen)o;
			if(arg instanceof ChildManagementScreen.UpdateList)//make inner class
			{
			view= new ChildManagementScreen();
			data.getChildrenList();
			}
			else if(arg instanceof ChildManagementScreen.Children)
			{
			ChildManagementScreen child=new ChildManagementScreen();
			ChildManagementScreen.Children ch=child.new Children();
			ch=(ChildManagementScreen.Children)arg;//
			data.addChild(new Children(ch.getName(),ch.getId()));
			}
			else
			{
			data.deleteChild((int) arg);
			}
		}
		else if(o instanceof GeneralGameBuilder)
		{
			Gs=(GeneralGameBuilder)o;
			GeneralGameBuilder gd=new GeneralGameBuilder();
			GeneralGameBuilder.GameDetails details=gd.new GameDetails();
			details=(GeneralGameBuilder.GameDetails)arg;//
			data.saveGameDetails(new GameRecord(new Children(details.getName()),details.getScore(),details.getGametype()));
		}
	}
} 
