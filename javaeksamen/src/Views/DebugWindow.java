
package Views;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Game.Move;
import Network.GameData;
import Network.Klient;
import Network.Server;

public class DebugWindow extends JFrame implements ActionListener {

	private Server srv;
	private Klient k;
	
	public DebugWindow(String title) {		
		addComponents();
		setupFrame(title);
		log("Ferdig med oppsett");
	}
	
	public DebugWindow() {		
		addComponents();
		setupFrame("Checkers debug");
		log("Ferdig med oppsett");
	}

	private void addComponents() {
		ta  = new JTextArea();
		ta.setAutoscrolls(true);
		ta.setEditable(false);
		t = new JTextField();		
		sendButton = new JButton("send");
		
		getRootPane().setDefaultButton(sendButton);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
					
		c.add(new JScrollPane(ta),BorderLayout.CENTER);	
		
		JPanel cmdPanel = new JPanel();
		cmdPanel.setLayout(new BorderLayout());
		cmdPanel.add(t,BorderLayout.CENTER);
		cmdPanel.add(sendButton, BorderLayout.EAST);
		
		c.add(cmdPanel, BorderLayout.SOUTH);
					
		sendButton.addActionListener(this);

	}

	public void log(String s) {
		ta.append(s+"\r\n");
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			
			doCmd();	
		
		} catch (Exception ex){
			log(ex.getMessage());
		}
	}

	private void doCmd() {
		String txt = t.getText().toLowerCase().trim();
		log("command: "+ txt);
		Commands cmd = Commands.valueOf(txt);
		
		switch(cmd) {
			case clear:
				ta.setText("");
				break;
			case server:
//				srv = new Server();
//				srv.start();
				break;
			case klient:
			case client:
				k = new Klient(this);
				k.start();
				break;
			case send:
				k.send(new Move());
				break;
			case close:
			case exit:
				System.exit(0);
				break;
			default:
				break;
		}
		t.setText("");
	}
	
	private enum Commands{
		server, klient, client, clear, send, close,exit
	}
	
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	doCmd();
	    }

	}

	private void setupFrame(String title) {
		setTitle(title);
		setVisible(true);
		setFontAndSize();
		setLocation(50,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setFontAndSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		

		setSize(800, 600);
		
		if(screenSize.width <= 1024){
			ta.setFont(getFont().deriveFont(new Float(16)));
		} else {
			ta.setFont(getFont().deriveFont(new Float(26)));
		}
	}

	
	static JTextArea ta;
	private static final long serialVersionUID = 1L;
	private JTextField t;
	private JButton sendButton;
}
