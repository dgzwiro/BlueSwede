package myAwt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import epic.Main;
import epic.buildings.BasicBarracks;

public class EconomicPopupMenu extends JPopupMenu implements ActionListener {

	/**
	 * 
	 */

	JMenuItem barracks;
	JMenuItem reset;
	int posX;
	int posY;

	private static final long serialVersionUID = -6515353868890175083L;

	public EconomicPopupMenu(int nPosX, int nPosY) {

		super();

		posX = nPosX;
		posY = nPosY;

		barracks = new JMenuItem("Barracks");
		barracks.addActionListener(this);
		add(barracks);
		
		reset = new JMenuItem("Reset");
		reset.addActionListener(this);
		add(reset);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == barracks) {
			((SectorPanel) MyFrame.sectorPanel).createTempBuilding(new BasicBarracks(posX, posY));
		} else if (e.getSource() == reset){
			Main.reset();
		}
	}
	
	

}
