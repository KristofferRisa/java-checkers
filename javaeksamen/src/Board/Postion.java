package Board;

import java.io.Serializable;

public class Postion implements Serializable {
	
	public Postion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;
}
