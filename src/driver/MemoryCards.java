package driver;
import model.*;
import view.*;
import controller.*;
import java.util.Vector;
public class MemoryCards {
public static void main(String[] args) {
		
		MainScreen view = new MainScreen();//Opening MainScreen
		//GeneralGameBuilder gameWindow = new GeneralGameBuilder.Builder().FirstPlayersName("Kiril").setDifficulty("Easy").build();
	//	Data model= new Data();
		AgainstComputer compM=new AgainstComputer();
		GeneralGameBuilder gameWindow = new GeneralGameBuilder();//.Builder().FirstPlayersName("Kiril").setDifficulty("Medium").SecondPlayersName("Liat").build();
		Game game;
		Controller controller = new GameController(view, gameWindow, compM);
		view.addObserver(controller);
		compM.addObserver(controller);
		gameWindow.addObserver(controller);
	//	game.addObserver(controller);

		/**
		 * ADD view as observer to controller (uncomment your view)
		 */
		
		
	}
}
