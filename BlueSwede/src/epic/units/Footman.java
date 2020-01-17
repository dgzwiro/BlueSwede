package epic.units;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import epic.CollisionDetector;
import epic.Main;
import epic.Orders;
import epic.Unit;
import javafx.util.Pair;

public class Footman implements Unit {

	private double coordX, coordY;
	//private int targetX, targetY;
	private Pair <Integer, Integer> targetCoords;
	private List<Pair<Integer, Integer>> midCoordinates = new ArrayList<>();
	private Pair <Integer, Integer> currentMidCoords;

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
	public synchronized void setTarget(int newTargetX, int newTargetY) {
		System.out.println("Setting target in footman");
		this.targetCoords = new Pair<>(newTargetX, newTargetY);
		midCoordinates = new ArrayList<>();
		midCoordinates.add(targetCoords);
		currentMidCoords = midCoordinates.get(0);
		Main.addMovingUnit(this);
	}

	@Override
	public void move() {
		if (!(coordX == currentMidCoords.getKey() && coordY == currentMidCoords.getValue())) {
			double stepX, stepY, dist;

			dist = Math.sqrt(Math.pow(currentMidCoords.getKey() - coordX, 2) + Math.pow(currentMidCoords.getValue() - coordY, 2));

			if (dist < 1) {
				coordX = currentMidCoords.getKey();
				coordY = currentMidCoords.getValue();
				return;
			}

			stepX = (currentMidCoords.getKey() - coordX) / dist;
			stepY = (currentMidCoords.getValue() - coordY) / dist;

			// System.out.println("StepX: " + stepX + " StepY:" + stepY + "
			// Dist: " + dist);

			coordX += stepX;
			coordY += stepY;

			if (CollisionDetector.detectAgainstAll(this)) {
				coordX -= stepX;
				coordY -= stepY;
			}
		}
		else {
			moveThroughMidCoords();
		}
	}

	private void moveThroughMidCoords() {
		if(!midCoordinates.isEmpty() && midCoordinates.iterator().hasNext()) {
			this.currentMidCoords = midCoordinates.iterator().next();
		} else {
			Main.removeMovingUnit(this);
		}
	}

	public Footman(int coordX2, int coordY2) {
		this.setCoordX(coordX2);
		this.setCoordY(coordY2);
	}

	@Override
	public double[] getCoords() {
		return new double[]{ getCoordX(), getCoordY() };
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
		if (point.getX() >= coordX && point.getX() <= coordX + size && point.getY() >= coordY && point.getY() <= coordY + size) {
				return true;
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
