package epic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import epic.enivormental.Tree;
import epic.terrain.Terrain;

public class FileParser {

	private static Pattern mapPattern = Pattern
			.compile("MapsizeX:(\\d+).*MapsizeY:(\\d+).*PlacedObjects(.*)TerrainObjects(.*)");
	private static Pattern placeablePattern = Pattern.compile("X:(\\d+).*Y:(\\d+).*type:([A-Z]+)Value:(\\d+)");
	private static Pattern terrainPattern = Pattern.compile("X(\\d+)Y(\\d+)([A-Z]+)");
	private static String fileLocation = "map.txt";
	
	public static void setFileLocation(String fileLocationFromUser) {
		//fileLocation = fileLocationFromUser;
		fileLocation = "map.txt";
	}
	
	public static void saveMapFile() {
		//System.out.println("Trying to save file");

		StringBuilder fileContent = new StringBuilder();

		fileContent.append("MapsizeX:" + Map_Main.getMapTilesX() + "MapsizeY:" + Map_Main.getMapTilesY());
		fileContent.append("PlacedObjects");
		for (Placeable object : Map_Main.getAllPlacedObjects()) {
			fileContent.append(object);
			fileContent.append("#");
		}

		fileContent.append("TerrainObjects");
		for (int i = 0; i < Map_Main.getMapTilesX(); i++) {
			for (int j = 0; j < Map_Main.getMapTilesY(); j++) {
				fileContent.append("X" + i + "Y" + j + Map_Main.getTerrain()[i][j]);
				fileContent.append("#");
			}
		}

		PrintWriter savingFile = null;
		try {
			savingFile = new PrintWriter(fileLocation);
			savingFile.println(fileContent);
			//System.out.println("File saved");
		} catch (FileNotFoundException e) {
			//System.out.println("File not found.");
			e.printStackTrace();
		} finally {
			savingFile.close();
		}
	}

	public static void loadMapFile() {
		File file = new File(fileLocation);
		Scanner in = null;
		StringBuilder bufferedInput = new StringBuilder();

		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				bufferedInput.append(in.nextLine());
				bufferedInput.append("\n");
			}
			//System.out.println("File loaded");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}

		String input = bufferedInput.toString();

		Matcher mapMatch = mapPattern.matcher(input);
		if (mapMatch.lookingAt()) {
			int mapSizeX = Integer.parseInt(mapMatch.group(1));
			int mapSizeY = Integer.parseInt(mapMatch.group(2));
			String placeables = mapMatch.group(3);
			String tiles = mapMatch.group(4);

			List<Placeable> loadedPlaceables = new ArrayList<Placeable>();
			for (String placeable : placeables.split("#")) {
				//System.out.println(placeable);
				Matcher placeableMatch = placeablePattern.matcher(placeable);
				if (placeableMatch.lookingAt()) {

					switch (placeableMatch.group(3)) {
					case "TREE":
						Tree loadedTree = new Tree(Integer.parseInt(placeableMatch.group(1)),
								Integer.parseInt(placeableMatch.group(2)), Integer.parseInt(placeableMatch.group(4)));
						loadedPlaceables.add(loadedTree);
						break;
					default:
						break;
					}
				} else {
					//System.out.println("LINE DOES NOT MATCH!");
				}

			}

			Terrain[][] loadedTerrains = new Terrain[mapSizeX][mapSizeY];
			for (String tile : tiles.split("#")) {
				//System.out.println(tile);
				Matcher terrainMatch = terrainPattern.matcher(tile);

				if (terrainMatch.lookingAt()) {
					Terrain loadedTile = new Terrain();
					switch (terrainMatch.group(3)) {
					case "WATER":
						loadedTile.setTile(Terrain.Type.WATER);
						loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))]=loadedTile;
						break;
					case "GRASS":
						loadedTile.setTile(Terrain.Type.GRASS);
						loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))]=loadedTile;
						break;
					case "SAND":
						loadedTile.setTile(Terrain.Type.SAND);
						loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))]=loadedTile;
						break;
					case "MOUNTAIN":
						loadedTile.setTile(Terrain.Type.MOUNTAIN);
						loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))]=loadedTile;
						break;
					default:
						break;

					}
				} else {
					//System.out.println("LINE DOES NOT MATCH!");
				}
			}
			
			Map_Main.loadMap(mapSizeX, mapSizeY, loadedTerrains, loadedPlaceables);

		} else {
			System.err.println("FILE CORRUPTED");
		}
	}

}
