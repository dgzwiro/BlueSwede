package epic.units;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import epic.CollisionDetector;
import epic.Orders;
import epic.Unit;

public class Footman implements Unit {

    private double coordX;
    private double coordY;
    private int targetX;
    private int targetY;

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
            double stepX;
            double stepY;
            double dist;

            dist = Math.sqrt(Math.pow(targetX - coordX, 2) + Math.pow(targetY - coordY, 2));

            if (dist < 1) {
                coordX = targetX;
                coordY = targetY;
                return;
            }

            stepX = (targetX - coordX) / dist;
            stepY = (targetY - coordY) / dist;

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
        return new double[]{getCoordX(), getCoordY()};
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
        return (point.getX() >= coordX && point.getX() <= coordX + size) && (point.getY() >= coordY && point.getY() <= coordY + size);
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
        return Collections.emptyList();
    }

    @Override
    public void setCoords(int coordX, int coordY) {
        setCoordY(coordY);
        setCoordX(coordX);
    }

    @Override
    public String toString() {
        int value = 0;
        return "X:" + coordX + "Y:" + coordY + "type:FOOTMANValue:" + value;
    }

}
