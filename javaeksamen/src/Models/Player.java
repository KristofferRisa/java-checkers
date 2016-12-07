package Models;

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
	
	//Fields
	public String name;
	public Boolean isHuman;
}
