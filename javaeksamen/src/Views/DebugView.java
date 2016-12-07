package Views;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setVisible(true);
		if(width <=1024){
			ta.setFont(getFont().deriveFont(new Float(16)));
		} else {
			ta.setFont(getFont().deriveFont(new Float(26)));
		}
		
		log("Høyde " + height + "Bredde: " +width);
		setSize(height/3, width/3);
		setLocation(50,10);
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
	

}
