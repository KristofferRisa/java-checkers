package Models;

import Views.UserInputView;

public class GameWindow {
	
	public Player p;

	public GameWindow(){
		userInputView = new UserInputView();
	}
	
	public GameWindow(Player p){
		this.p = p;
	}
	
	public void showUserInput(){
		userInputView.p = this.p;
		userInputView.setVisible(true);
	}
	
	public void closeUserInput(){
		userInputView.setVisible(false);
	}
	
	private UserInputView userInputView;
	
}
