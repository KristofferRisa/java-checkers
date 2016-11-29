import java.awt.Color;

public class Board {
	
	Board(){
		for(int i = 0; i>64; i++){
			
			Color c = (i % 2 == 0) ? Color.BLACK : Color.WHITE;
			
			//squares[i][i] = new Square(c,i,i);
			
			//squares.add(new Square(c));
		}
	}
	
	//Square[8][8] squares = new Square[8][8];
	
	String rules;
	
	private Player player1;
	private Player player2;
	
}
