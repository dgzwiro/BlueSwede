package epic;

import epic.terrain.Terrain;

public final class CollisionDetector {

    public static boolean detectAll() {

        return false;
    }

    public static boolean detectAgainst(Placeable arg0, Placeable arg1) {
        if (arg0 == arg1) {
            return false;
        }

		return ((arg0.getCoords()[0] >= arg1.getCoords()[0] && arg0.getCoords()[0] <= arg1.getCoords()[0] + arg1.getSize()) && (arg0.getCoords()[1] >= arg1.getCoords()[1] && arg0.getCoords()[1] <= arg1.getCoords()[1] + arg1.getSize()))
				|| ((arg0.getCoords()[0] + arg0.getSize() >= arg1.getCoords()[0] && arg0.getCoords()[0] <= arg1.getCoords()[0] + arg1.getSize()) && (arg0.getCoords()[1] + arg0.getSize() >= arg1.getCoords()[1] && arg0.getCoords()[1] <= arg1.getCoords()[1] + arg1.getSize()))
				|| (arg1.getCoords()[0] + arg1.getSize() >= arg0.getCoords()[0] && arg1.getCoords()[0] <= arg0.getCoords()[0] + arg0.getSize()) && (arg1.getCoords()[1] + arg1.getSize() >= arg0.getCoords()[1] && arg1.getCoords()[1] <= arg0.getCoords()[1] + arg0.getSize());
	}

    public static boolean detectAgainstAll(Placeable newPlaced) {
//		System.out.println("Detecting for" +


        if (Main.getTerrain()[(int) (newPlaced.getCoords()[0] / 50)][(int) (newPlaced.getCoords()[1] / 50)].getType() == Terrain.Type.WATER) {
            return true;
        }

        for (Placeable oldPlaced : Main.getAllPlacedObjects()) {
            if (detectAgainst(newPlaced, oldPlaced)) {
                return true;
            }
        }
        return false;
    }

}
