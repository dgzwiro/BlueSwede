package epic.terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Terrain {

	public enum Type {
		WATER, GRASS, SAND, MOUNTAIN
	}
	
	private Type terrainType;
	private BufferedImage sprite;

	public Terrain() {
		terrainType = Type.WATER;
		setImage();
	}

	public void setTile(Type wantedTile) {
		terrainType = wantedTile;
		setImage();
	}
	
	public Type getType(){
		return terrainType;
	}
	
	private void setImage() {
		File srcImage;

		switch (terrainType) {
		case GRASS:
			srcImage = new File("BlueSwede/resource/img/grass.jpg");
			break;
		case MOUNTAIN:
			srcImage = new File("BlueSwede/resource/img/mountain.jpg");
			break;
		case SAND:
			srcImage = new File("BlueSwede/resource/img/sand.jpg");
			break;
		case WATER:
			srcImage = new File("BlueSwede/resource/img/water.jpg");
			break;
		default:
			System.err.println("Invalid terrain type created");
			throw new ExceptionInInitializerError(
					"Invalid terrain type created");
		}

		try {
			setSprite(ImageIO.read(srcImage));
		} catch (IOException e) {
			System.err.println("Error in loading image");
			System.err.println(srcImage.getAbsolutePath());
			e.printStackTrace();
			setSprite(new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB));
		}
	}

	private void setSprite(BufferedImage newSprite) {
		this.sprite = newSprite;
	}

	public BufferedImage getSprite() {
		return this.sprite;
	}

	@Override
	public String toString(){
		switch(this.terrainType){
		case GRASS:
			return "GRASS";
		case MOUNTAIN:
			return "MOUNTAIN";
		case SAND:
			return "SAND";
		case WATER:
			return "WATER";
		default:
			return "NULL";
		}
		
	}
}
