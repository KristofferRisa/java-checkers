package Views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;

import Models.Game;
import Models.Player;

public class UserInputView extends JPanel implements ActionListener  {

	private JButton button;

	private JCheckBox checkBox;

	public Game game;

	public UserInputView(){
		
		game = new Game();
		tf = new JTextField();
		JLabel l = new JLabel("Skriv inn navn:");
		button = new JButton("Start");
		checkBox = new JCheckBox("Server");
		
		setSize(400,500);
		setLayout(new GridLayout(4,0));
		
		add(l);
		add(tf);
		add(checkBox);
		add(button);
	
		button.addActionListener(this);	
	}
	
	private JTextField tf;
	
	public void actionPerformed(ActionEvent event) {
		if(checkBox.isSelected()){
			//Spiller 1
			game.player1.name = tf.getText();			
		} else {
			game.player2.name = tf.getText();
		}
		setVisible(false);
    }
	
}


