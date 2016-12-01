package Views;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Models.Player;

public class UserInputView extends JFrame {
	
	public UserInputView(){
		JTextField tf = new JTextField();
		JLabel l = new JLabel("Skriv inn navn:");
		
		setTitle("New game!");
		setSize(200,300);
		
		setLayout(new GridLayout());
		
		add(l);
		add(tf);
	
	}
	
	public Player p;
	
}
