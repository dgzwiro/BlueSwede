package epic.buildings;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import epic.Building;
import epic.Orders;

public class BasicBarracks implements Building {

    private double coordX;
    private double coordY;
    private static BufferedImage avatar;
    private static BufferedImage avatarBig;
    private static List<Orders> orders = new ArrayList<>();
    private static final int SIZE = 30;
    private static final int VALUE = 0;

    static {
        orders.add(Orders.FOOTMAN_PRODUCTION);
        orders.add(Orders.MARKSMAN_PRODUCTION);

        File srcImage = new File("BlueSwede/resource/img/basic_barracks.jpg");
        File srcImageBig = new File("BlueSwede/resource/img/basic_barracks_big.jpg");
        try {
            setAvatar(ImageIO.read(srcImage));
            setAvatarBig(ImageIO.read(srcImageBig));
        } catch (IOException e) {
            System.err.println("Error in loading image");
            e.printStackTrace();
            setAvatar(new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB));
        }
    }


    public BasicBarracks(int coordX, int coordY) {
        setCoords(coordX, coordY);
    }

    @Override
    public double[] getCoords() {
        return new double[]{getCoordX(), getCoordY()};
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
        return (point.getX() >= coordX && point.getX() <= coordX + SIZE) && (point.getY() >= coordY && point.getY() <= coordY + SIZE);
    }

    public int getSize() {
        return SIZE;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    private static void setAvatar(BufferedImage avatar) {
        BasicBarracks.avatar = avatar;
    }

    private static void setAvatarBig(BufferedImage avatar) {
        BasicBarracks.avatarBig = avatar;
    }

    @Override
    public BufferedImage getAvatarBig() {
        return avatarBig;
    }

    @Override
    public List<Orders> getOrders() {
        return orders;
    }

    @Override
    public void setCoords(int coordX, int coordY) {
        setCoordY(coordY);
        setCoordX(coordX);

    }

    @Override
    public String toString() {
        return "X:" + coordX + "Y:" + coordY + "type:BBARRACKValue:" + VALUE;
    }


}
