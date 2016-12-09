package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Models.Game;


public class UserInputView extends JPanel implements ActionListener {

	public UserInputView() {
		
		game = new Game();
		isServer = false;
		
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
		setVisible(true);
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

		} else if (width >= 1367 || width <= 2001) {

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
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnServer) {
			game.player1.name = nameField.getText();
			isServer = true;
		}
		if (e.getSource() == btnJoin) {
			game.player2.name = nameField.getText();
			isServer = false;
		}
		setVisible(false);
	}

	private JTextField nameField;

	private JButton btnServer;

	private JButton btnJoin;

	private JButton btnLocal;

	public Game game;

	public boolean isServer;

	private DebugView debug;

	private static final long serialVersionUID = -3671918994272350809L;

}
