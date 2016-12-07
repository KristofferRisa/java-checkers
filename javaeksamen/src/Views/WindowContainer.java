package Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import Board.BoardWindow;
import Models.Server;

public class WindowContainer extends JFrame {

	private GameView g;
	public WindowContainer(){
		configureFrame();
	}
	
	public void showUserInput(){
		userInputView = new UserInputView();
		add(userInputView);
	}
	
	public void showBoard(){
		boardWindow = new BoardWindow();
		remove(userInputView);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
				
		gc.weightx = 0;
		gc.weighty = 0;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(boardWindow,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		if(server != null){
			g = new GameView(server);
		}
		else {
			g = new GameView();
		}
		add(g,gc);
		
	}


	
	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
	}
	
	public UserInputView userInputView;
	private BoardWindow boardWindow;
	public Server server;
	private static final long serialVersionUID = -3425445318104341180L;
	
	
}
