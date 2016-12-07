package Models;

public class Player {
	
	//Constructor
	public Player(){
		isHuman = true;
	}
	
	public Player(String name, Boolean ishuman){
		name = name;
		alias = name;
		isHuman = ishuman;
	}
	
	//Fields
	public String name;
	public String alias;
	public Boolean isHuman;
}
