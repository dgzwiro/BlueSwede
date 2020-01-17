package epic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import epic.terrain.Terrain;
import epic.units.Footman;
import myAwt.MyFrame;
import myAwt.MyFrame.*;

public class Main {

	private static List<Unit> movingUnits = new ArrayList<>();
	private static List<Unit> movingUnitsPreparedForRemoval = new ArrayList<>();

	public static void addMovingUnit(Unit unit) {
		movingUnits.add(unit);
	}
	public static void removeMovingUnit(Unit unit) {
		movingUnitsPreparedForRemoval.add(unit);
	}
	private static void removeMovingUnit(List<Unit> unitsToRemove) {
		movingUnits.remove(unitsToRemove);
	}
	public static List<Unit> getMovingUnits() {
		return movingUnits;
	}

	public enum State {
		STRATEGIC, TACTICAL, ECONOMIC, ECONOMIC_PLACING
	}
	private static List<Building> buildings = new ArrayList<>();

	private static List<Unit> units = new ArrayList<>();

	private static State activeState;

	private static Placeable activeSelection;
	private static List<Unit> activeGroupSelection = new ArrayList<>();

	private static JFrame mainFrame;
	
	private static List<Placeable> allPlacedObjects = new ArrayList<>();

	private static int mapSizeX;
	private static int mapSizeY;

	private static int mapTilesX = 30;
	private static int mapTilesY = 15;

	static {
		mapSizeX = (mapTilesX * 50);
		mapSizeY = (mapTilesY * 50);
	}

	public static void main(String[] args) {

		setActiveState(State.ECONOMIC);

		new Thread(new Runnable() {

			public void run() {
				mainFrame = new MyFrame();
			}
		}).run();

		new Thread(new Runnable() {
			Long timeMillis = System.currentTimeMillis();

			public void run() {
				while (true) {
					if (System.currentTimeMillis() - timeMillis >= 30
							&& mainFrame != null) {
						timeMillis = System.currentTimeMillis();
						mainFrame.repaint();
						if (!getMovingUnits().isEmpty()) {
							for (Unit unit : getMovingUnits()) {
								unit.move();
							}
							removeMovingUnit(movingUnitsPreparedForRemoval);
						}
					}
				}
			}
		}).run();


	}

	public static State getActiveState() {
		return activeState;
	}

	public static void setActiveState(State activeState) {
		Main.activeState = activeState;
	}

	public static List<Building> getBuildings() {
		return buildings;
	}

	public static void addBuilding(Building newBuilding) {
		buildings.add(newBuilding);
		allPlacedObjects.add(newBuilding);
	}

	public static List<Unit> getUnits() {
		return units;
	}

	public static void addUnit(Unit newUnit) {
		units.add(newUnit);
		allPlacedObjects.add(newUnit);
	}

	public static Placeable getActiveSelection() {
		return activeSelection;
	}

	public static void setActiveSelection(Placeable newActiveSelection) {
		Main.activeSelection = newActiveSelection;
	}

	public static List<Unit> getActiveGroupSelection() {
		return activeGroupSelection;
	}

	public static void addActiveGroupSelection(Unit unit) {
		Main.activeGroupSelection.add(unit);
	}

	public static void cleanActiveGroupSelection() {
		Main.activeGroupSelection.clear();
	}

	public static void reset() {
		buildings = new ArrayList<>();
		units = new ArrayList<>();
		activeState = State.ECONOMIC;
		activeSelection = null;
	}
	
	private static Terrain[][] terrain = new Terrain[mapTilesX][mapTilesY];

	static {
		for (int i = 0; i < mapTilesX; i++) {
			for (int j = 0; j < mapTilesY; j++) {
				terrain[i][j] = new Terrain();
			}
		}
	}

	public static List<Placeable> getAllPlacedObjects() {
		return allPlacedObjects;
	}

	public static void addPlacedObjects(Placeable placedObject) {
		allPlacedObjects.add(placedObject);
	}

	public static Terrain[][] getTerrain() {
		return terrain;
	}

	public static void changeTerrrain(int posX, int posY, Terrain wantedTerrain) {
		int index_x = posX / 50;
		int index_y = posY / 50;

		terrain[index_x][index_y] = wantedTerrain;
	}

	public static int getMapSizeX() {
		return mapSizeX;
	}

	public static int getMapSizeY() {
		return mapSizeY;
	}

	public static int getMapTilesX() {
		return mapTilesX;
	}

	public static int getMapTilesY() {
		return mapTilesY;
	}

	public static void setMapSize(int tilesX, int tilesY) {
		mapTilesX = tilesX;
		mapTilesY = tilesY;

		mapSizeX = (tilesX * 50);
		mapSizeY = (tilesY * 50);
	}

	public static void loadMap(int tilesX, int tilesY, Terrain[][] loadedTerrain, List<Placeable> placedObjects){
		setMapSize(tilesX, tilesY);
		terrain = loadedTerrain;
		allPlacedObjects = placedObjects;
		reset();
	}

}
