package graphics;

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

import board.Postion;
import game.Move;
import network.DataTransferObject;
import network.Klient;
import network.Server;

public class GamePanel extends JPanel implements ActionListener  {

	private Klient klient;

	public GamePanel(Klient klient){
		this.klient = klient;
		setupGameView();
	}
	
	public GamePanel(){

		setupGameView();
	}

	private void setupGameView() {
		JLabel l = new JLabel("Checkers 1.0");
		JTextArea ta = new JTextArea();
		msgButton = new JButton("Send melding til klient!");
		closeButton = new JButton("Lukk meg!");
		//setLayout(new GridLayout());
		setLayout(new GridBagLayout());
		add(l);
		add(ta);
		add(closeButton);
		add(msgButton);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int height = screenSize.height;
		int width = screenSize.width;
		setVisible(true);
		
		if(width <=1366){
			ta.setFont(getFont().deriveFont(new Float(16)));
			msgButton.setFont(getFont().deriveFont(new Float(16)));
			closeButton.setFont(getFont().deriveFont(new Float(16)));
		} else if (width <=1367 && width <=2001) {
			ta.setFont(getFont().deriveFont(new Float(28)));
			msgButton.setFont(getFont().deriveFont(new Float(28)));
			closeButton.setFont(getFont().deriveFont(new Float(28)));
		}
		
		else {
			ta.setFont(getFont().deriveFont(new Float(28)));
			msgButton.setFont(getFont().deriveFont(new Float(28)));
			closeButton.setFont(getFont().deriveFont(new Float(28)));
		}

		closeButton.addActionListener(this);
		msgButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == closeButton) {
			System.exit(0);	
		}
		if(e.getSource() == msgButton){			
			System.out.println("fors�ker � sende game til klient!");
			Move move = new Move();
			move.fromPostion = new Postion(1, 2);
			klient.send(move);	
	
		}
	}
	
	private JButton msgButton;
	private JButton closeButton;
	
	private static final long serialVersionUID = 7542084159328657810L;

}