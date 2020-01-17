package myAwt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import epic.FileParser;
import epic.Map_Main;

public class MyFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JPanel sectorPanel;
	static JPanel sectorToolPanel;

	static JButton openKitFrame;
	static JButton saveMap;
	static JButton loadMap;
	
	public MyFrame() {
		super("Hello world");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Map_Main.getMapSizeX() + 30, Map_Main.getMapSizeY() + 80);
		setResizable(false);
		setLocation(5, 5);
		setBackground(Color.BLACK);

		setLayout(new BorderLayout());

		openKitFrame = new JButton("Creation Kit");
		openKitFrame.addActionListener(this);
		
		saveMap = new JButton("Save Map");
		saveMap.addActionListener(this);
		
		loadMap = new JButton("Load Map");
		loadMap.addActionListener(this);

		sectorPanel = new SectorPanel();
		sectorToolPanel = new JPanel();

		sectorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sectorToolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		sectorToolPanel.setLayout(new GridLayout());
		sectorToolPanel.add(openKitFrame);
		sectorToolPanel.add(saveMap);
		sectorToolPanel.add(loadMap);

		add(sectorPanel, BorderLayout.CENTER);
		add(sectorToolPanel, BorderLayout.PAGE_START);

		setVisible(true);
		//System.out.println(System.currentTimeMillis());
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(openKitFrame)) {
			Map_Main.openCreationKit();
		} else if (source.equals(saveMap)) {
			FileParser.setFileLocation(Map_Main.getStringFromUser());
			FileParser.saveMapFile();
		} else if (source.equals(loadMap)) {
			FileParser.setFileLocation(Map_Main.getStringFromUser());
			FileParser.loadMapFile();
		}

	}
	
}
