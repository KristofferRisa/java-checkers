package Views;

import javax.swing.JFrame;

public class WindowContainer extends JFrame {

	public UserInputView userInputView;
	
	public WindowContainer(){

		configureFrame();
		
		userInputView = new UserInputView();
		add(userInputView);
	}
	
	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
}
