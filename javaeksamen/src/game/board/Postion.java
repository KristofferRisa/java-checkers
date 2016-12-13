package game.board;

import java.io.Serializable;

public class Postion implements Serializable {
	
	private static final long serialVersionUID = 7869009785423747634L;
	public Postion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;
}
