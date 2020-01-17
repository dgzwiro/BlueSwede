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
import epic.Main;
import epic.Main.State;

public class MyFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton economicButton = new JButton("Economic");
	JButton militaryButton = new JButton("Military");
	JButton exitButton = new JButton("Exit");
	JButton loadMap = new JButton("Load Map");

	static JPanel sectorPanel;
	static JPanel sectorToolPanel;
	static JPanel sectorActiveSelectionPanel;

	public MyFrame() {
		super("Hello world");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Main.getMapSizeX() + 30, Main.getMapSizeY() + 80);
		setResizable(false);
		setLocation(5, 5);

		setLayout(new BorderLayout());

		economicButton.addActionListener(this);
		militaryButton.addActionListener(this);
		loadMap.addActionListener(this);
		exitButton.addActionListener(this);

		sectorPanel = new SectorPanel();
		sectorToolPanel = new JPanel();
		sectorActiveSelectionPanel = new SectorActiveSelectionPanel();

		sectorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sectorToolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sectorActiveSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		sectorToolPanel.setLayout(new GridLayout());

		sectorToolPanel.add(economicButton);
		sectorToolPanel.add(militaryButton);
		sectorToolPanel.add(loadMap);
		sectorToolPanel.add(exitButton);

		add(sectorPanel, BorderLayout.CENTER);
		add(sectorToolPanel, BorderLayout.PAGE_START);
		add(sectorActiveSelectionPanel, BorderLayout.PAGE_END);

		setVisible(true);
		//System.out.println(System.currentTimeMillis());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == economicButton) {
			Main.setActiveState(State.ECONOMIC);
		} else if (source == militaryButton) {
			switch (Main.getActiveState()) {
			case STRATEGIC:
				Main.setActiveState(State.TACTICAL);
				break;
			default:
				Main.setActiveState(State.STRATEGIC);
				break;
			}
		} else if (source.equals(loadMap)) {
			FileParser.loadMapFile();
		} else if (source == exitButton) {
			System.exit(0);
		}
	}

}
