package myAwt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MyStringFrame extends JFileChooser implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyStringFrame () {
		super("Eye of Sauron");
		setSize(700, 200);
		setLocation(5, 5);
		
		setLayout(new GridLayout());
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
