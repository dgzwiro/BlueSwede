package myAwt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import epic.enivormental.Tree;
import epic.terrain.Terrain;

public class MyKitFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton treeBttn;
	JButton grassBttn;
	JButton waterBttn;
	JButton sandBttn;
	
	public MyKitFrame () {
		super("Garden of Eden Creation Kit");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 200);
		setResizable(false);
		setLocation(5, 5);
		
		treeBttn = new JButton("Tree");
		treeBttn.addActionListener(this);
		
		grassBttn = new JButton("Grass");
		grassBttn.addActionListener(this);
		
		waterBttn = new JButton("Water");
		waterBttn.addActionListener(this);
		
		sandBttn = new JButton("Sand");
		sandBttn.addActionListener(this);
		
		setLayout(new GridLayout());
		
		add(treeBttn);
		add(grassBttn);
		add(waterBttn);
		add(sandBttn);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//System.out.println(source.toString());
		
		if(source.equals(treeBttn)){
			((SectorPanel) MyFrame.sectorPanel).createTempPlaceable(new Tree(0, 0));
		} else if (source.equals(grassBttn)){
			Terrain wantedTerrain = new Terrain();
			wantedTerrain.setTile(Terrain.Type.GRASS);
			((SectorPanel) MyFrame.sectorPanel).prepareTerrainChange(wantedTerrain);
		} else if (source.equals(waterBttn)){
			Terrain wantedTerrain = new Terrain();
			wantedTerrain.setTile(Terrain.Type.WATER);
			((SectorPanel) MyFrame.sectorPanel).prepareTerrainChange(wantedTerrain);
		} else if (source.equals(sandBttn)){
			Terrain wantedTerrain = new Terrain();
			wantedTerrain.setTile(Terrain.Type.SAND);
			((SectorPanel) MyFrame.sectorPanel).prepareTerrainChange(wantedTerrain);
		}
				
	}

}
