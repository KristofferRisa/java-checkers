package game;

import java.io.Serializable;

public class Player implements Serializable {	

	//Constructor
	public Player(){
		isHuman = true;
	}
	
	public Player(String n, Boolean human){
		name = n;
		isHuman = human;
	}
	
	public String name;
	
	public Boolean isHuman;

	private static final long serialVersionUID = -8157042324487733910L;

}
