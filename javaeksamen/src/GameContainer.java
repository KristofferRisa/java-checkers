
public class GameContainer {
	
	GameContainer(){
		
		System.out.println("Hei.. Skriv inn spiller navn: /n");
		String playerName = System.console().readLine();
		
		player1 = new Player(playerName,true);
		player2 = new Player("Robot", false);
		
		game = new Game(player1, player2);
		
	}
	
	private Game game;
	private Player player1;
	private Player player2;
	
}
