package Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Models.Server;

public class GameView extends JPanel implements ActionListener  {

	public GameView(Server s){
		server = s;
		setupGameView();
	}
	
	public GameView(){

		setupGameView();
	}

	private void setupGameView() {
		JLabel l = new JLabel("Checkers 1.0");
		JTextArea ta = new JTextArea();
		b2 = new JButton("Send melding til klient!");
		b = new JButton("Lukk meg!");
		//setLayout(new GridLayout());
		setLayout(new GridBagLayout());
		add(l);
		add(ta);
		add(b);
		add(b2);
		

		b.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b) {
			System.exit(0);	
		}
		if(e.getSource() == b2){
			if(server != null){
				System.out.println("forsøker å sende game til klient!");
				server.sendGame();	
			}
		}
	}
	
	private Server server;

	private JButton b2;
	private JButton b;
	
	private static final long serialVersionUID = 7542084159328657810L;

}
