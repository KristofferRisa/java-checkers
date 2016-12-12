package graphics.usercontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.MissingResourceException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import datamodels.UserInput;
import game.Checker;
import game.Player;


public class StartPanel extends JPanel implements ActionListener {

	private JFrame frame;

	private UserInput userInput;

	public UserInput getUserInputData(){
		if(userInput != null){
			return userInput;
		}
		//TODO: add exception handling
		throw new MissingResourceException("Missing userinput data", getName(), "UserInput");
	}
	
	public StartPanel(JFrame frame) {
		this.frame = frame;
		JLabel nameLabel = new JLabel("Username: ");
		nameField = new JTextField(10);
		btnServer = new JButton("Create New Online Game");
		btnJoin = new JButton("Join Online Game/Enter IP");
		btnLocal = new JButton("Test Game Locally");
		

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;

		gc.weightx = 0.5;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridy = 0;
		add(nameLabel, gc);
		
		gc.fill = 1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);

		// First button - Create Game -------------------
		gc.gridx = 1;
		gc.gridy = 2;
		add(btnServer, gc);

		// Second button - Join Game -------------------
		gc.gridx = 1;
		gc.gridy = 3;
		add(btnJoin, gc);
		
		// Third button - Test Game -------------------
		gc.gridx = 1;
		gc.gridy = 4;
		add(btnLocal, gc);
		btnServer.addActionListener(this);
		btnJoin.addActionListener(this);
		
		// Puts relative sizes on all JComponents
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		if (width <= 1366) {
			nameLabel.setFont(getFont().deriveFont(new Float(16)));
			nameField.setFont(getFont().deriveFont(new Float(16)));
			nameField.setFont(getFont().deriveFont(new Float(16)));
			btnServer.setFont(getFont().deriveFont(new Float(16)));
			btnJoin.setFont(getFont().deriveFont(new Float(16)));
			btnLocal.setFont(getFont().deriveFont(new Float(16)));

			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 10)));

		} else if (width >= 1367 && width <= 2001) {

			nameLabel.setFont(getFont().deriveFont(new Float(26)));
			nameField.setFont(getFont().deriveFont(new Float(26)));
			btnServer.setFont(getFont().deriveFont(new Float(26)));
			btnJoin.setFont(getFont().deriveFont(new Float(26)));
			btnLocal.setFont(getFont().deriveFont(new Float(26)));

			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 20)));
		}

		else {

			nameLabel.setFont(getFont().deriveFont(new Float(36)));
			nameField.setFont(getFont().deriveFont(new Float(36)));
			btnServer.setFont(getFont().deriveFont(new Float(36)));
			btnJoin.setFont(getFont().deriveFont(new Float(36)));
			btnLocal.setFont(getFont().deriveFont(new Float(36)));

			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 30)));
		}
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnServer) {
			userInput = new UserInput();
			userInput.name = nameField.getText();
			userInput.isServer = true;
		}
		if (e.getSource() == btnJoin) {
			userInput = new UserInput();
			userInput.name = nameField.getText();
			userInput.isServer = false;
		}		
		frame.getContentPane().repaint();
		setVisible(false);
	}
	
	private JTextField nameField;

	private JButton btnServer;

	private JButton btnJoin;

	private JButton btnLocal;

	private static final long serialVersionUID = -3671918994272350809L;

}
