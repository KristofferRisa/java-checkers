package graphics;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfoPanel extends JPanel {

	private static final long serialVersionUID = -6452446960664157639L;

	public UserInfoPanel(String name){
		JLabel nameLabel = new JLabel(name);
		setLayout(new BorderLayout());
		add(nameLabel,BorderLayout.WEST);
	}
	
}
