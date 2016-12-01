package Views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;

import Models.Player;

public class UserInputView extends JPanel implements ActionListener  {
	
	private JTextField tf;

	public UserInputView(){
		tf = new JTextField();
		JLabel l = new JLabel("Skriv inn navn:");
		JButton b = new JButton("Start");
		
		setSize(400,500);
		
		setLayout(new GridLayout());
		
		add(l);
		add(tf);
		add(b);
	
		b.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if(p != null){
			p.name = tf.getText();
		}
		setVisible(false);
    }

	public Player p;
	
}


