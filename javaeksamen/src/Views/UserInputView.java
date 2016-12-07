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

import Models.GameConfig;
import Models.Player;

public class UserInputView extends JPanel implements ActionListener  {

	private JButton button;

	private JCheckBox checkBox;

	public UserInputView(GameConfig config){
		this.config = config;
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
	
	public Player p;
	public GameConfig config;
	
	public void actionPerformed(ActionEvent event) {
		if(p != null && config != null){
			p.name = tf.getText();
			config.isServer = checkBox.isSelected();
		}
		setVisible(false);
    }
	
}


