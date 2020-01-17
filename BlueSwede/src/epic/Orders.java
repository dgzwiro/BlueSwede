package epic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import epic.units.Footman;



public enum Orders {
	FootmanProduction("BlueSwede/resource/icons/footman_icon.jpg"),
	MarksmanProduction("BlueSwede/resource/icons/marksman_icon.jpg");
	
	private Icon img;
	
	Orders(String imgPath){
		try {
			setImg(new ImageIcon(ImageIO.read(new File(imgPath)),name()));
		} catch (IOException e) {
			System.err.println("Error in loading Icon!");
			e.printStackTrace();
		}
	}
	
	public void ExecuteOrder(double coordX, double coordY){
		switch(this){
		case FootmanProduction:
			Footman newFootman = new Footman((int) coordX, (int) coordY); 
			Main.addUnit(newFootman);
			while(CollisionDetector.detectAgainstAll(newFootman)){
				newFootman.setCoordX((int) (newFootman.getCoordX()+1));
//				newFootman.setCoordY((int) coordY);
			}
			break;
		case MarksmanProduction:
			break;
		default:
			break;
			
		}
	}

	public Icon getImg() {
		return img;
	}

	private void setImg(Icon img) {
		this.img = img;
	}
}
