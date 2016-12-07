package Views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameView extends JPanel implements ActionListener  {

	public GameView(){
		setSize(400,500);
		
		JLabel l = new JLabel("Checkers 1.0");
		JTextArea ta = new JTextArea();
		JButton b = new JButton("Lukk meg!");
		setLayout(new GridLayout());
		
		add(l);
		add(ta);
		add(b);

		b.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
	}
}
