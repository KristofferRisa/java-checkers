import Models.Game;
import Models.Player;
import Models.GameWindow;

public class Checkers {
	
	public Checkers(){

		p1 = new Player();
		GameWindow gui = new GameWindow();
		gui.p = p1;
		
		Player p2 = new Player("Robot", false);
		
		gui.showUserInput();
		
		
		game = new Game(p1, p2);
		//game.start();
		
	}
	
	private Game game;
	
	private Player p1;
	
}
