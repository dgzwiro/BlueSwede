package myAwt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import epic.Building;
import epic.CollisionDetector;
import epic.Main;
import epic.Main.State;
import epic.Placeable;
import epic.Unit;
import epic.terrain.Terrain;

public class SectorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421331081425396197L;

	private MyRectangle2D selectionSquare = new MyRectangle2D();
	private Point2D selectionStartPoint = null;
	private Building temporaryBuilding = null;

	public SectorPanel() {
		setPreferredSize(new Dimension(Main.getMapSizeX(), Main.getMapSizeY()));
		PopupListener mouseListener = new PopupListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

	}

	public JPopupMenu menu;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int row = 0;
		int column = 0;
		for (Terrain[] terrainRow : Main.getTerrain()) {
			column = 0;
			for (Terrain singleTerrain : terrainRow) {
				g2d.drawImage(singleTerrain.getSprite(), row * 50, column * 50, this);
				column++;
			}
			row++;
		}

		for (Placeable placedObject : Main.getAllPlacedObjects()) {
			g2d.drawImage(placedObject.getAvatar(), (int) placedObject.getCoords()[0],
					(int) placedObject.getCoords()[1], this);
		}

		g2d.drawString(Main.getActiveState().toString(), 10, 20);

		g2d.draw(selectionSquare);

		if (temporaryBuilding != null) {
			if (CollisionDetector.detectAgainstAll(temporaryBuilding)) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.GREEN);
			}
			g2d.fillRect((int) temporaryBuilding.getCoords()[0], (int) temporaryBuilding.getCoords()[1],
					temporaryBuilding.getSize(), temporaryBuilding.getSize());
		}
	}

	public void createTempBuilding(Building tempBuilding) {
		this.temporaryBuilding = tempBuilding;
		Main.setActiveState(State.ECONOMIC_PLACING);
	}

	class PopupListener extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {

			selectionSquare.setFrameFromDiagonal(selectionStartPoint.getX(), selectionStartPoint.getY(), e.getX(),
					e.getY());

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (Main.getActiveState() == State.ECONOMIC_PLACING) {
				temporaryBuilding.setCoords(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				doPopUp(e);
			} else {
				if (selectionStartPoint == null)
					selectionStartPoint = new Point2D.Double(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				doPopUp(e);
			} else {
				if (Main.getActiveState() == State.ECONOMIC_PLACING) {
					if (!CollisionDetector.detectAgainstAll(temporaryBuilding)) {
						Main.addBuilding(temporaryBuilding);
						temporaryBuilding = null;
						Main.setActiveState(State.ECONOMIC);
					}
				} else {
					Main.cleanActiveGroupSelection();
					Main.setActiveSelection(null);

					for (Building building : Main.getBuildings()) {
						if (building.contains(e.getPoint())) {
							Main.setActiveSelection(building);
							break;
						}
					}

					for (Unit unit : Main.getUnits()) {
						if (selectionSquare.contains(unit) || unit.contains(e.getPoint())) {
							Main.addActiveGroupSelection(unit);
						}
					}

					if (!Main.getActiveGroupSelection().isEmpty()) {
						Main.setActiveSelection(null);
						Main.setActiveSelection(Main.getActiveGroupSelection().get(0));
					}

				}

				selectionSquare = new MyRectangle2D();
				selectionStartPoint = null;
			}
		}

		private void doPopUp(MouseEvent e) {

			switch (Main.getActiveState()) {
			case ECONOMIC:
				EconomicPopupMenu eMenu = new EconomicPopupMenu(e.getX(), e.getY());
				eMenu.show(e.getComponent(), e.getX(), e.getY());
				break;
			case STRATEGIC:
				if (!Main.getActiveGroupSelection().isEmpty()) {
					for (Unit unit : Main.getActiveGroupSelection()) {
						unit.setTarget(e.getX(), e.getY());
					}
				}
				break;
			case TACTICAL:
				break;
			default:
				break;

			}
		}
	}

}
