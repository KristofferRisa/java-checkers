package graphics.usercontrol;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.board.Postion;
import network.GameDataTransferObject;
import network.Client;
import network.Server;
import network.data.Move;

public class GameControlPanel extends JPanel implements ActionListener  {

	private Client klient;
	private JTextArea textArea;

	public GameControlPanel(Client klient){
		this.klient = klient;
		setupGameView();
	}
	
	public GameControlPanel(){

		setupGameView();
	}

	private void setupGameView() {
		setupComponets();
		
		setDimensions();

		addActionListeners();
	}

	private void addActionListeners() {
		closeButton.addActionListener(this);
		msgButton.addActionListener(this);
	}

	private void setupComponets() {
		JLabel label = new JLabel("Checkers 1.0");
		textArea = new JTextArea();
		msgButton = new JButton("Send melding til klient!");
		closeButton = new JButton("Lukk meg!");
		//setLayout(new GridLayout());
		setLayout(new GridBagLayout());
		add(label);
		add(textArea);
		add(closeButton);
		add(msgButton);
	}

	private void setDimensions() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int height = screenSize.height;
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
		if(e.getSource() == closeButton) {
			System.exit(0);	
		}
		if(e.getSource() == msgButton){			
			System.out.println("forsøker å sende game til klient!");
			Move move = new Move();
			move.fromPostion = new Postion(1, 2);
			klient.send(move);	
	
		}
	}
	
	private JButton msgButton;
	private JButton closeButton;
	
	private static final long serialVersionUID = 7542084159328657810L;

}
