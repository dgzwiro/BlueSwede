package epic;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Placeable {

	public double[] getCoords();
	public boolean contains(Point2D point);
	public int getSize();
	public BufferedImage getAvatar();
	public void setCoords(int x, int y);
	public Placeable Clone();
	
}
