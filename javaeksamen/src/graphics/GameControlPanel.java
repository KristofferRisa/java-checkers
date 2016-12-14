package graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.Move;
import network.Client;


public class GameControlPanel extends JPanel implements ActionListener  {


	private Client klient;
	private JTextArea textArea;
//	private GameDataTransferObject data;

	public GameControlPanel(Client klient){
		this.klient = klient;
//		this.data = klient.data;
		setupGameView();
	}
	
	private void setupGameView() {
		setupComponets();
		
		setDimensions();

	}


	private void setupComponets() {
		JTextField t = new JTextField("test");		
		JButton sendButton = new JButton("send");
//		JLabel playerName = new JLabel(data.player1.name);
		JPanel cmdPanel = new JPanel();
		cmdPanel.setLayout(new BorderLayout());
		cmdPanel.add(t,BorderLayout.CENTER);
		cmdPanel.add(sendButton, BorderLayout.EAST);
		
		add(cmdPanel, BorderLayout.SOUTH);

		
		textArea = new JTextArea();
		msgButton = new JButton("Send melding til klient!");
		closeButton = new JButton("Lukk meg!");

		setLayout(new GridBagLayout());
//		add(label);
		add(textArea);
		add(closeButton);
		add(msgButton);
		
	}
	

	private void setMenuBar(MenuBar mbar) {
		// TODO Auto-generated method stub
		
	}

	private void setDimensions() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		setVisible(true);
		
		if(width <=1366){
			textArea.setFont(getFont().deriveFont(new Float(16)));
			msgButton.setFont(getFont().deriveFont(new Float(16)));
			closeButton.setFont(getFont().deriveFont(new Float(16)));
		} else if (width <=1367 && width <=2001) {
			textArea.setFont(getFont().deriveFont(new Float(28)));
			msgButton.setFont(getFont().deriveFont(new Float(28)));
			closeButton.setFont(getFont().deriveFont(new Float(28)));
		}	
		else {
			textArea.setFont(getFont().deriveFont(new Float(28)));
			msgButton.setFont(getFont().deriveFont(new Float(28)));
			closeButton.setFont(getFont().deriveFont(new Float(28)));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeButton) {
			System.exit(0);
		}
		if (e.getSource() == msgButton) {
			System.out.println("forsøker å sende game til klient!");
			

		}
	}

	private JButton msgButton;
	private JButton closeButton;

	private static final long serialVersionUID = 7542084159328657810L;

}
