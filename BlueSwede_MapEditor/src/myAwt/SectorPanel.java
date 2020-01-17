package myAwt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import epic.CollisionDetector;
import epic.Map_Main;
import epic.Map_Main.State;
import epic.Placeable;
import epic.terrain.Terrain;

public class SectorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421331081425396197L;

	private Placeable temporaryObject = null;
	private Terrain preparedTerrain = null;

	public SectorPanel() {
		setPreferredSize(new Dimension(Map_Main.getMapSizeX(), Map_Main.getMapSizeY()));
		setBackground(Color.BLACK);
		PopupListener mouseListener = new PopupListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

	}

	public JPopupMenu menu;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawString(Map_Main.getActiveState().toString(), 10, 20);

		int row = 0;
		int column = 0;
		for (Terrain[] terrainRow : Map_Main.getTerrain()) {
			column = 0;
			for (Terrain singleTerrain : terrainRow) {
				g2d.drawImage(singleTerrain.getSprite(), row * 50, column * 50,
						this);
				column++;
			}
			row++;
		}
		
		if (temporaryObject != null) {
			if (CollisionDetector.detectAgainstAll(temporaryObject)) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.GREEN);
			}
			g2d.fillRect((int) temporaryObject.getCoords()[0],
					(int) temporaryObject.getCoords()[1],
					temporaryObject.getSize(), temporaryObject.getSize());
		}

		for (Placeable placedObject : Map_Main.getAllPlacedObjects()) {
			g2d.drawImage(placedObject.getAvatar(),
					(int) placedObject.getCoords()[0],
					(int) placedObject.getCoords()[1], this);
		}

	}

	public void createTempPlaceable(Placeable tempPlaceable) {
		preparedTerrain = null;
		this.temporaryObject = tempPlaceable;
		Map_Main.setActiveState(State.PLACING);
	}

	class PopupListener extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (Map_Main.getActiveState() == State.PLACING && temporaryObject != null) {
				temporaryObject.setCoords(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				doPopUp(e);
			} else {

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				doPopUp(e);
			} else {
				if (Map_Main.getActiveState() == State.PLACING) {
					if (temporaryObject != null) {
						if (!CollisionDetector
								.detectAgainstAll(temporaryObject)) {
							Map_Main.addPlacedObjects(temporaryObject.Clone());
						}
					}
					if (preparedTerrain != null) {
						Map_Main.changeTerrrain(e.getX(), e.getY(), preparedTerrain);
					}
				}
			}
		}

		private void doPopUp(MouseEvent e) {
			if (Map_Main.getActiveState() == State.PLACING) {
				temporaryObject = null;
				preparedTerrain = null;
				Map_Main.setActiveState(State.PLANNING);
			}
		}
	}

	public void prepareTerrainChange(Terrain wantedTerrain) {
		temporaryObject = null;
		preparedTerrain = wantedTerrain;
		Map_Main.setActiveState(State.PLACING);
	}

}
