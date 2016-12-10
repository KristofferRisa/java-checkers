package Views;

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

import Network.Server;

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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int height = screenSize.height;
		int width = screenSize.width;
		setVisible(true);
		
		if(width <=1366){
			ta.setFont(getFont().deriveFont(new Float(16)));
			b2.setFont(getFont().deriveFont(new Float(16)));
			b.setFont(getFont().deriveFont(new Float(16)));
		} else if (width <=1367 && width <=2001) {
			ta.setFont(getFont().deriveFont(new Float(28)));
			b2.setFont(getFont().deriveFont(new Float(28)));
			b.setFont(getFont().deriveFont(new Float(28)));
		}
		
		else {
			ta.setFont(getFont().deriveFont(new Float(28)));
			b2.setFont(getFont().deriveFont(new Float(28)));
			b.setFont(getFont().deriveFont(new Float(28)));
		}

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
//				server.sendGame();	
			}
		}
	}
	
	private Server server;

	private JButton b2;
	private JButton b;
	
	private static final long serialVersionUID = 7542084159328657810L;

}
