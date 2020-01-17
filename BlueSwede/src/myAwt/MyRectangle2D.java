package myAwt;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import epic.Unit;

public class MyRectangle2D extends Rectangle2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773718048763073348L;
	
	public boolean contains(Unit arg0){
		
		return contains(new Point2D.Double(arg0.getCoords()[0], arg0.getCoords()[1]));
		
	}


}
