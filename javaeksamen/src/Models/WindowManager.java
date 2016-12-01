package Models;

import javax.swing.JFrame;

import Views.DebugView;
import Views.UserInputView;

public class WindowManager extends JFrame {
	
	public Player p;

	public WindowManager(){
		
		userInputView = new UserInputView();
		setTitle("Checkers 1.0!! (java eksamen)");
		setVisible(true);
		setSize(400,500);
		
		add(userInputView);
		
		debug = new DebugView();
		
	}
	
	public void showUserInput(){
		userInputView.p = this.p;
		userInputView.setVisible(true);
	}
	
	public void closeUserInput(){
		userInputView.setVisible(false);
		notifyAll();
	}
	
	private UserInputView userInputView;
	public DebugView debug;
	
	
	public boolean userInputIsActive(){
		return (userInputView.isVisible() == true) ? true : false;
	}
	
}
