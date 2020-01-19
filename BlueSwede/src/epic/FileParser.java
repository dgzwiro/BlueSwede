package epic;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void loadMapFile() {
        String fileLocation = "map.txt";
        File file = new File(fileLocation);
        System.out.println(file.getAbsolutePath());
        Scanner in = null;
        StringBuilder bufferedInput = new StringBuilder();

        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                bufferedInput.append(in.nextLine());
                bufferedInput.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        String input = bufferedInput.toString();

        Matcher mapMatch = mapPattern.matcher(input);
        if (mapMatch.lookingAt()) {
            int mapSizeX = Integer.parseInt(mapMatch.group(1));
            int mapSizeY = Integer.parseInt(mapMatch.group(2));
            String placeables = mapMatch.group(3);
            String tiles = mapMatch.group(4);

            List<Placeable> loadedPlaceables = new ArrayList<>();
            for (String placeable : placeables.split("#")) {
                Matcher placeableMatch = placeablePattern.matcher(placeable);
                if ((placeableMatch.lookingAt()) && ("TREE".equals(placeableMatch.group(3)))) {
                    Tree loadedTree = new Tree(Integer.parseInt(placeableMatch.group(1)),
                            Integer.parseInt(placeableMatch.group(2)), Integer.parseInt(placeableMatch.group(4)));
                    loadedPlaceables.add(loadedTree);
                }

            }

            Terrain[][] loadedTerrains = new Terrain[mapSizeX][mapSizeY];
            for (String tile : tiles.split("#")) {
                Matcher terrainMatch = terrainPattern.matcher(tile);

                if (terrainMatch.lookingAt()) {
                    Terrain loadedTile = new Terrain();
                    switch (terrainMatch.group(3)) {
                        case "WATER":
                            loadedTile.setTile(Terrain.Type.WATER);
                            loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))] = loadedTile;
                            break;
                        case "GRASS":
                            loadedTile.setTile(Terrain.Type.GRASS);
                            loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))] = loadedTile;
                            break;
                        case "SAND":
                            loadedTile.setTile(Terrain.Type.SAND);
                            loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))] = loadedTile;
                            break;
                        case "MOUNTAIN":
                            loadedTile.setTile(Terrain.Type.MOUNTAIN);
                            loadedTerrains[Integer.parseInt(terrainMatch.group(1))][Integer.parseInt(terrainMatch.group(2))] = loadedTile;
                            break;
                        default:
                            break;

                    }
                }
            }

            Main.loadMap(mapSizeX, mapSizeY, loadedTerrains, loadedPlaceables);

        } else {
            System.err.println("FILE CORRUPTED");
        }
    }

}
