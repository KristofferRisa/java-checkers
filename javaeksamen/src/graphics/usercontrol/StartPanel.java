package graphics.usercontrol;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.MissingResourceException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sun.javafx.scene.layout.region.Margins.Converter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import datamodels.UserInput;
import game.Checker;
import game.Player;


public class StartPanel extends JPanel implements ActionListener {

	private JFrame frame;

	private UserInput userInput;

	private JLabel changePort;

	public UserInput getUserInputData(){
		if(userInput != null){
			return userInput;
		}
		
		throw new MissingResourceException("Missing userinput data", getName(), "UserInput");
	}
	
	public StartPanel(JFrame frame) {

		this.frame = frame;
		JLabel nameLabel = new JLabel("Username: ");
		nameField = new JTextField("Enter a username");
		JLabel ipLabel = null;
		try {
			ipLabel = new JLabel(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		btnServer = new JButton("Create New Online Game");
		ipField = new JTextField("Enter IP here ", 10);
		btnJoin = new JButton("Join Online Game/Enter IP");
		btnLocal = new JButton("Test Game Locally");
		btnWhatIP = new JButton("?");
		changePort = new JLabel("Change port ");
		portField = new JTextField("55660");
		JLabel localipLabel = new JLabel("127.0.0.1");
		// Added together in a panel for better GUI
		JPanel p = new JPanel();
		p.add(ipField);
		p.add(btnWhatIP);
		changePort.setVisible(false);
		portField.setVisible(false);
		btnWhatIP.setToolTipText("Open whatsmyip.org");
		//GridBagLayot
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = 1;
		
		//Username Label
		gc.gridx = 0;
		gc.gridy = 0;
		add(nameLabel, gc);
		//Username Field
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		//Remove text when mouse click on Username Field
		nameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nameField.setText("");
			}
		});
		// Get IP Label 
		gc.gridx = 0;
		gc.gridy = 2;
		add(ipLabel, gc);
		// Create Game Button
		gc.gridx = 1;
		gc.gridy = 2;
		add(btnServer, gc);
		// Enter IP and What is my IP button

		gc.gridx = 0;
		gc.gridy = 3;
		add(p, gc);
		
		
		//Remove text when mouse click on IP Field
		ipField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ipField.setText("");
			}
		});
		// Join New Online Game Button
		gc.gridx = 1;
		gc.gridy = 3;
		add(btnJoin, gc);
		// Test Game Locally Button
		gc.gridx = 1;
		gc.gridy = 4;
		add(btnLocal, gc);
		// Local IP Label -----
		gc.gridx = 0;
		gc.gridy = 4;
		add(localipLabel, gc);
		// Change Port Label
		gc.gridx = 0;
		gc.gridy = 5;
		add(changePort, gc);
		// Change Port Text Field
		gc.gridx = 1;
		gc.gridy = 5;
		add(portField, gc);
		// Action Listeners
		btnServer.addActionListener(this);
		btnJoin.addActionListener(this);
		btnWhatIP.addActionListener(this);
		
		
		// Puts relative sizes on all JComponents
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		if (width <= 1366) {
			
			nameLabel.setFont(getFont().deriveFont(new Float(16)));
			ipLabel.setFont(getFont().deriveFont(new Float(16)));
			nameField.setFont(getFont().deriveFont(new Float(16)));
			nameField.setFont(getFont().deriveFont(new Float(16)));
			btnServer.setFont(getFont().deriveFont(new Float(16)));
			btnJoin.setFont(getFont().deriveFont(new Float(16)));
			btnLocal.setFont(getFont().deriveFont(new Float(16)));
			changePort.setFont(getFont().deriveFont(new Float(16)));
			portField.setFont(getFont().deriveFont(new Float(16)));
			ipField.setFont(getFont().deriveFont(new Float(16)));
			btnWhatIP.setFont(getFont().deriveFont(new Float(16)));
			localipLabel.setFont(getFont().deriveFont(new Float(16)));
			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 10)));

		} else if (width >= 1367 && width <= 2001) {

			nameLabel.setFont(getFont().deriveFont(new Float(26)));
			ipLabel.setFont(getFont().deriveFont(new Float(26)));
			nameField.setFont(getFont().deriveFont(new Float(26)));
			btnServer.setFont(getFont().deriveFont(new Float(26)));
			btnJoin.setFont(getFont().deriveFont(new Float(26)));
			btnLocal.setFont(getFont().deriveFont(new Float(26)));
			changePort.setFont(getFont().deriveFont(new Float(26)));
			portField.setFont(getFont().deriveFont(new Float(26)));
			ipField.setFont(getFont().deriveFont(new Float(26)));
			btnWhatIP.setFont(getFont().deriveFont(new Float(26)));
			localipLabel.setFont(getFont().deriveFont(new Float(26)));

			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 20)));
		}

		else {

			nameLabel.setFont(getFont().deriveFont(new Float(36)));
			ipLabel.setFont(getFont().deriveFont(new Float(36)));
			nameField.setFont(getFont().deriveFont(new Float(36)));
			btnServer.setFont(getFont().deriveFont(new Float(36)));
			btnJoin.setFont(getFont().deriveFont(new Float(36)));
			btnLocal.setFont(getFont().deriveFont(new Float(36)));
			changePort.setFont(getFont().deriveFont(new Float(36)));
			portField.setFont(getFont().deriveFont(new Float(36)));
			btnWhatIP.setFont(getFont().deriveFont(new Float(36)));
			localipLabel.setFont(getFont().deriveFont(new Float(36)));
			
			setBorder(BorderFactory.createTitledBorder(null,
					"Checkers Java 1.0!", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("", Font.PLAIN, 30)));
		}
		
		setVisible(true);
		

	}
	

	
	

	public void showPortfield() {
		changePort.setVisible(true);
		
		portField.setVisible(true);
				
	}

	
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == btnServer) {
			getValuesFromUserInterface(true);
		}
		if (e.getSource() == btnJoin) {
			getValuesFromUserInterface(false);
		}		
		if (e.getSource() == btnWhatIP) {
			try {
				URI uri = new URI("http://www.whatsmyip.org/");
				Desktop dt = Desktop.getDesktop();
				dt.browse(uri);
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	
	}

	private void getValuesFromUserInterface(Boolean b) {
		userInput = new UserInput();
		userInput.name = nameField.getText();
		userInput.portNumber = Integer.parseInt(portField.getText());
		userInput.isServer = b;
		frame.getContentPane().repaint();
		setVisible(false);
	}
	
	private JTextField nameField;
	
	private JTextField ipField;

	private JButton btnServer;

	private JButton btnJoin;

	private JButton btnLocal;
	
	private JButton btnWhatIP;
	
	private JTextField portField;

	private static final long serialVersionUID = -3671918994272350809L;

}
