package Views;

import javax.swing.JFrame;

import Board.BoardWindow;

public class WindowContainer extends JFrame {

	public UserInputView userInputView;
	private BoardWindow boardWindow;
	
	public WindowContainer(){

		configureFrame();
		setVisible(true);
		
		
	}
	
	public void showUserInput(){
		userInputView = new UserInputView();
		add(userInputView);
	}
	
	public void showBoard(){
		boardWindow = new BoardWindow();
		remove(userInputView);
		add(boardWindow);
	}
	
	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
}
