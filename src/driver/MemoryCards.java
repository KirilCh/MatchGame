package driver;
import model.*;
import view.*;
import controller.*;
import java.util.Vector;

public class MemoryCards {
public static void main(String[] args) {
		
		//MainScreen view = new MainScreen();//Opening MainScreen
		//GeneralGameBuilder gameWindow = new GeneralGameBuilder.Builder().FirstPlayersName("Kiril").setDifficulty("Easy").build();
		Data model=new Data();
		AgainstComputer compM=new AgainstComputer();
		GeneralGameBuilder gameWindow = new GeneralGameBuilder();//.Builder().FirstPlayersName("Kiril").setDifficulty("Medium").SecondPlayersName("Liat").build();
		StatisticsScreen statpage=new StatisticsScreen();
		ChildManagementScreen managechild=new ChildManagementScreen();
		MainScreen view = new MainScreen(statpage,managechild);
		Controller controller1=new DataController(model,view,gameWindow,statpage,managechild);
		Controller controller = new GameController(view, gameWindow, compM);
		view.addObserver(controller);
		compM.addObserver(controller);
		gameWindow.addObserver(controller);
		view.addObserver(controller1);
		model.addObserver(controller1);
		statpage.addObserver(controller1);
		managechild.addObserver(controller1);
		gameWindow.addObserver(controller1);
		
	//	game.addObserver(controller);

		/**
		 * ADD view as observer to controller (uncomment your view)
		 */
				
		}
}

