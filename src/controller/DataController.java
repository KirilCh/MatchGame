package controller;
import java.util.Vector;

import model.Data;
import view.*;
import java.util.Observable;

public class DataController implements Controller{
	private MainScreen mainSview;
	private ChildManagementScreen childrenView; 
	private Data dataModel;
	private View lastUpdateRequestView;

	public DataController(Data model, MainScreen view)//update all the relevant class attributes....
	{
		dataModel = model;
		mainSview = view;
	}
	@Override
	public void update(Observable o, Object arg) {
	/*	if(o instanceof Data) {
			if(lastUpdateRequestView instanceof MainScreen)
				mainSview.updateList((Vector)arg);
			if(lastUpdateRequestView instanceof ChildManagementScreen)
				childrenView.updateList((Vector)arg);
			if(lastUpdateRequestView instanceof StatisticsScreen)//insert the correct statistics class name
				mainSview.updateList((Vector)arg);
		}else if(o instanceof MainScreen){
			lastUpdateRequestView=new MainScreen();
			dataModel.getChildrenList();
		}else if(o instanceof ChildManagementScreen){
			lastUpdateRequestView=new ChildManagementScreen();
			dataModel.getChildrenList();
		}else if(o instanceof StatisticsScreen){
			lastUpdateRequestView=new StatisticsScreen();
			dataModel.getChildrenList();
		}	*/	
	}


}
