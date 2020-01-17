package epic.enivormental;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import epic.Orders;
import epic.Resource;

public class Tree implements Resource {

	private double coordX;
	private double coordY;
	private int value;
	private static BufferedImage avatar;

	static {

		File srcImage = new File("BlueSwede/resource/img/tree.jpg");
		try {
			setAvatar(ImageIO.read(srcImage));
		} catch (IOException e) {
			System.err.println("Error in loading image");
			e.printStackTrace();
			setAvatar(new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB));
		}
	}

	private static final int size = 10;

	public Tree(int coordX, int coordY) {
		setCoords(coordX, coordY);
		setValue(2000);
	}

	public Tree(int coordX, int coordY, int newValue) {
		setCoords(coordX, coordY);
		setValue(newValue);
	}

	@Override
	public double[] getCoords() {
		double[] coords = { getCoordX(), getCoordY() };
		return coords;
	}

	public double getCoordX() {
		return coordX;
	}

	private void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	private void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	@Override
	public boolean contains(Point2D point) {
		if (point.getX() >= coordX && point.getX() <= coordX + size) {
			if (point.getY() >= coordY && point.getY() <= coordY + size) {
				return true;
			}
		}
		return false;
	}
	
	public int getSize() {
		return size;
	}

	public BufferedImage getAvatar() {
		return avatar;
	}

	private static void setAvatar(BufferedImage avatar) {
		Tree.avatar = avatar;
	}

	@Override
	public void setCoords(int coordX, int coordY) {
		setCoordY(coordY);
		setCoordX(coordX);
	}
	
	private void setValue(int newValue) {
		this.value = newValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String toString(){
		return "X:" + coordX + "Y:" + coordY + "type:TREEValue:" + getValue();
	}

	@Override
	public BufferedImage getAvatarBig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
