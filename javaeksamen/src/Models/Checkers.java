package Models;

public class Checkers extends Thread {
	
	public Checkers(){

		p1 = new Player();
		WindowManager gui = new WindowManager();
		gui.p = p1;
		
		Player p2 = new Player("Robot", false);
		
		gui.showUserInput(); 
		while(gui.userInputIsActive() == true){
			try{
				sleep(1000);
			}
			catch(InterruptedException e){
				gui.debug.log(e.getMessage());
			}
			  
			gui.debug.log("Venter på user input");
		}
		
		gui.debug.log(p1.name);
		
		game = new Game(p1, p2);
		//game.start();
		
	}
	
	private Game game;
	
	private Player p1;
	
}
