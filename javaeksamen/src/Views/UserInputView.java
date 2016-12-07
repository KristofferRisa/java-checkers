package Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;

import Models.GameConfig;
import Models.Player;

public class UserInputView extends JPanel implements ActionListener  {

	public Player p;
	
	public GameConfig config;
	
	private JTextField nameField;

	private JButton btnServer;

	private JButton btnJoin;

	private JButton btnLocal;

	public UserInputView(GameConfig c){
		
		config = c;
		setBorder(BorderFactory.createTitledBorder("Type in your username and create a new game or join an existing one!"));
		
		JLabel nameLabel = new JLabel ("Username: ");
		
		nameField = new JTextField(10);
		
		btnServer = new JButton("Create New Online Game");
		btnJoin = new JButton("Join Online Game/Enter IP");
		btnLocal = new JButton("Test Game Locally");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		
		//Label and TextField  -------------------
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		
		//First button - Create Game  -------------------
		gc.gridx = 1;
		gc.gridy = 2;
		add(btnServer, gc);
		
		//Second button - Join Game  -------------------
		gc.gridx = 1;
		gc.gridy = 3;
		add(btnJoin, gc);
		
		gc.weighty = 10;
		gc.weightx = 10;
		
		//Third button - Test Game  -------------------
		gc.gridx = 1;
		gc.gridy = 4;
		add(btnLocal, gc);
		btnServer.addActionListener(this);
		btnJoin.addActionListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(p != null && config != null){
			
			p.name = nameField.getText();
			
		if(e.getSource() == btnServer)
			config.isServer = true;
		}
		if(e.getSource() == btnJoin){
			config.isServer = false;
		}
		setVisible(false);
    }
	
}


