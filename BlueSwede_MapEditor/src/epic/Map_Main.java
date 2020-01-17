package epic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import myAwt.MyFrame;
import myAwt.MyKitFrame;
import myAwt.MyStringFrame;
import epic.terrain.Terrain;

public class Map_Main {

	public enum State {
		PLANNING, PLACING;
	}

	private static State activeState;

	private static JFrame mainFrame;
	private static JFrame kitFrame;
	private static JFileChooser stringFrame;


	private static List<Placeable> allPlacedObjects = new ArrayList<Placeable>();

	private static int mapSizeX;
	private static int mapSizeY;

	private static int mapTilesX = 30;
	private static int mapTilesY = 15;

	static {
		mapSizeX = (mapTilesX * 50);
		mapSizeY = (mapTilesY * 50);
	}

	private static Terrain[][] terrain = new Terrain[mapTilesX][mapTilesY];

	static {
		for (int i = 0; i < mapTilesX; i++) {
			for (int j = 0; j < mapTilesY; j++) {
				terrain[i][j] = new Terrain();
			}
		}
	}

	public static void main(String[] args) {

		setActiveState(State.PLANNING);

		new Thread(new Runnable() {

			public void run() {
				mainFrame = new MyFrame();
			}
		}).run();
		;

		openCreationKit();

		new Thread(new Runnable() {
			Long timeMillis = System.currentTimeMillis();

			public void run() {
				while (true) {
					if (System.currentTimeMillis() - timeMillis >= 30
							&& mainFrame != null) {
						timeMillis = System.currentTimeMillis();
						mainFrame.repaint();
						if (kitFrame != null) {
							kitFrame.repaint();
						}
						if (stringFrame != null) {
							stringFrame.repaint();
						}
					}
				}
			}
		}).run();
		;

	}

	public static State getActiveState() {
		return activeState;
	}

	public static void setActiveState(State activeState) {
		Map_Main.activeState = activeState;
	}

	public static void reset() {
		activeState = State.PLANNING;
	}

	public static void openCreationKit() {
		kitFrame = null;

		new Thread(new Runnable() {

			public void run() {
				kitFrame = new MyKitFrame();
			}
		}).run();
		;
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

	

	public static String getStringFromUser() {
		stringFrame = null;

		new Thread(new Runnable() {

			public void run() {
				stringFrame = new MyStringFrame();
			}
		}).run();
		;
		return null;
	}
}
