package epic;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

public interface Placeable {

	public double[] getCoords();
	public int getSize();
	public BufferedImage getAvatar();
	public BufferedImage getAvatarBig();
	public List<Orders> getOrders();
	void setCoords(int coordX, int coordY);
	boolean contains(Point2D point);
}
