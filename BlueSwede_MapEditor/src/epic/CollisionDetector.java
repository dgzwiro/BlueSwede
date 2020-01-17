package epic;

import java.awt.geom.Point2D;

import epic.terrain.Terrain;

public final class CollisionDetector {

	public static boolean detectAll() {

		return false;
	}

	public static boolean detectAgainst(Placeable arg0, Placeable arg1) {
		if (arg0.contains(new Point2D.Double(arg1.getCoords()[0], arg1
				.getCoords()[1])) && arg0 != arg1) {
			//System.out.println("Detected collision");
			return true;
		}
		if (arg0.contains(new Point2D.Double(arg1.getCoords()[0]
				+ arg1.getSize(), arg1.getCoords()[1] + arg1.getSize()))
				&& arg0 != arg1) {
			//System.out.println("Detected collision");
			return true;
		}
		if (arg0.contains(new Point2D.Double(arg1.getCoords()[0]
				+ arg1.getSize(), arg1.getCoords()[1]))
				&& arg0 != arg1) {
			//System.out.println("Detected collision");
			return true;
		}
		if (arg0.contains(new Point2D.Double(arg1.getCoords()[0], arg1.getCoords()[1] + arg1.getSize()))
				&& arg0 != arg1) {
			//System.out.println("Detected collision");
			return true;
		}
		return false;
	}

	public static boolean detectAgainstAll(Placeable newPlaced) {
		if(Map_Main.getTerrain()[(int) (newPlaced.getCoords()[0]/50)][(int) (newPlaced.getCoords()[1]/50)].getType() != Terrain.Type.GRASS){
			return true;
		}
		for(Placeable oldPlaced : Map_Main.getAllPlacedObjects()){
			if(detectAgainst(newPlaced, oldPlaced)){
				return true;
			}
		}
		return false;
	}

}
