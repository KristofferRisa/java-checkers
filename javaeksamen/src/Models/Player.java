package Models;

import java.io.Serializable;

public class Player implements Serializable {
	
	//Constructor
	public Player(){
		isHuman = true;
	}
	
	public Player(String name, Boolean ishuman){
		this.name = name;
		alias = name;
		isHuman = ishuman;
	}
	
	//Fields
	public String name;
	public String alias;
	public Boolean isHuman;
}
