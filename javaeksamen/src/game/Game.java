package game;

import datamodels.GameDataTransferObject;
import graphics.DebugWindowFrame;
import network.Server;
import network.data.Move;

public class Game extends Thread {
	
	private Server server;
	private RuleEngine ruleEngine;
	private DebugWindowFrame debug;
	private GameDataTransferObject data;

	public Game(Server server, RuleEngine ruleEngine, DebugWindowFrame debug){
		this.server = server;
		this.ruleEngine = ruleEngine;
		this.debug = debug;
		this.data = new GameDataTransferObject();
		
	}
	
	public void run() {
		while(server.client1.isClientConneted == false 
				&& server.client2.isClientConneted == false){
			try {
				debug.log("Venter på klienter. Status klient 1: " + server.client1.isClientConneted + ". Status klient 2 " + server.client2.isClientConneted );
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data.player1 = server.client1.data.player1;
		data.player2 = server.client2.data.player2;
//		data.game = game;
//		    	
    	while(isActive){
			//Spillet kan starte - annen hver tur
			
			server.client1.send(data);
			Move move1 = server.client1.recive();
			
			ruleEngine.update(move1);
			
			server.client2.send(data);
			Move move2 = server.client2.recive();
			
			ruleEngine.update(move2);
		}
	}
	
	public void restart(){
		throw new UnsupportedOperationException();
	}
	
	public void save(){
		throw new UnsupportedOperationException();
	}
	
	public void load(){
		throw new UnsupportedOperationException();
	}
	
	public boolean isActive;
}
