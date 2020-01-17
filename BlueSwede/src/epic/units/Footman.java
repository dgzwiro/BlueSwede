package epic.units;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import epic.CollisionDetector;
import epic.Orders;
import epic.Unit;

public class Footman implements Unit {

	private double coordX, coordY;
	private int targetX, targetY;

	private static BufferedImage avatarBig;
	private static BufferedImage avatar;

	static {
		File srcImageBig = new File("BlueSwede/resource/img/footman_big.jpg");
		try {
			setAvatarBig(ImageIO.read(srcImageBig));
		} catch (IOException e) {
			System.err.println("Error in loading image");
			e.printStackTrace();
		}
	}

	static {
		File srcImage = new File("BlueSwede/resource/img/footman.jpg");
		try {
			setAvatar(ImageIO.read(srcImage));
		} catch (IOException e) {
			System.err.println("Error in loading image");
			e.printStackTrace();
		}
	}

	private static int size = 5;

	@Override
	public void setTarget(int newTargetX, int newTargetY) {
		this.targetX = newTargetX;
		this.targetY = newTargetY;
	}

	@Override
	public void move() {
		if (!(coordX == targetX && coordY == targetY)) {
			double stepX, stepY, dist;

			dist = Math.sqrt(Math.pow(targetX - coordX, 2) + Math.pow(targetY - coordY, 2));

			if (dist < 1) {
				coordX = targetX;
				coordY = targetY;
				return;
			}

			stepX = (targetX - coordX) / dist;
			stepY = (targetY - coordY) / dist;

			// System.out.println("StepX: " + stepX + " StepY:" + stepY + "
			// Dist: " + dist);

			coordX += stepX;
			coordY += stepY;

			if (CollisionDetector.detectAgainstAll(this)) {
				coordX -= stepX;
				coordY -= stepY;
			}
		}

	}

	public Footman(int coordX2, int coordY2) {
		this.setCoordX(coordX2);
		this.setCoordY(coordY2);
		this.setTarget(coordX2, coordY2);
	}

	@Override
	public double[] getCoords() {
		double[] coords = { getCoordX(), getCoordY() };
		return coords;
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
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

	@Override
	public int getSize() {
		return size;
	}

	private static void setAvatar(BufferedImage avatar) {
		Footman.avatar = avatar;
	}

	@Override
	public BufferedImage getAvatar() {
		return avatar;
	}

	private static void setAvatarBig(BufferedImage avatar) {
		Footman.avatarBig = avatar;
	}

	@Override
	public BufferedImage getAvatarBig() {
		return avatarBig;
	}

	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCoords(int coordX, int coordY) {
		setCoordY(coordY);
		setCoordX(coordX);
	}

	@Override
	public String toString(){
		return "X:" + coordX + "Y:" + coordY + "type:FOOTMANValue:" + getValue();
	}

	private int getValue() {
		
		return 0;
	}

}
