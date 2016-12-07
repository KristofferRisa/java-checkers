package Views;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DebugView extends JFrame implements ActionListener {

	public DebugView() {
		setTitle("Checkers debug");
		ta  = new JTextArea();
		b = new JButton("clear");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(b,BorderLayout.SOUTH);
		c.add(new JScrollPane(ta),BorderLayout.CENTER);
		setSize(500,800);
		xpos -= 500;
		setLocation(xpos,10);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}

	public void log(String s) {
		ta.append(s+"\r\n");
	}
	
	public void actionPerformed(ActionEvent e) {
		ta.setText("");
	}

	JTextArea ta;
	JButton b;
	
	static final int SCREENWIDTH = 1250;
	static int xpos = SCREENWIDTH;
}
