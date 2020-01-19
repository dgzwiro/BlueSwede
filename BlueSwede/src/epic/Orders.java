package epic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import epic.units.Footman;



public enum Orders {
	FOOTMAN_PRODUCTION("BlueSwede/resource/icons/footman_icon.jpg"),
	MARKSMAN_PRODUCTION("BlueSwede/resource/icons/marksman_icon.jpg");
	
	private Icon img;
	
	Orders(String imgPath){
		try {
			setImg(new ImageIcon(ImageIO.read(new File(imgPath)),name()));
		} catch (IOException e) {
			System.err.println("Error in loading Icon!");
			e.printStackTrace();
		}
	}
	
	public void executeOrder(double coordX, double coordY){
		if (this == Orders.FOOTMAN_PRODUCTION) {
			Footman newFootman = new Footman((int) coordX, (int) coordY);
			Main.addUnit(newFootman);
			while (CollisionDetector.detectAgainstAll(newFootman)) {
				newFootman.setCoordX((int) (newFootman.getCoordX() + 1));
			}
		}
	}

	public Icon getImg() {
		return img;
	}

	private void setImg(Icon img) {
		this.img = img;
	}
}
