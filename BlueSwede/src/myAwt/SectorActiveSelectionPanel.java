package myAwt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import epic.Main;
import epic.Orders;
import epic.Placeable;

public class SectorActiveSelectionPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9219820979542056599L;

	private HashMap<JButton, Orders> orders = new HashMap<JButton, Orders>();

	Placeable showedSelection;

	public SectorActiveSelectionPanel() {
		setPreferredSize(new Dimension(1024, 100));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		switch (Main.getActiveState()) {
		case STRATEGIC:
		case ECONOMIC:
			if (Main.getActiveSelection() == null) {
				g2d.fillRect(0, 0, 100, 100);
				cleanUp();
			} else {
				if (!(Main.getActiveSelection() == showedSelection)) {
					init(Main.getActiveSelection());
				}
				drawBuilding(g, Main.getActiveSelection());
			}
			break;
		case TACTICAL:
			break;
		default:
			break;

		}
	}

	private void drawBuilding(Graphics g, Placeable selectable) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(selectable.getAvatarBig(), 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton button : orders.keySet()) {
			if (e.getSource() == button) {
				orders.get(button).executeOrder(showedSelection.getCoords()[0] + ((showedSelection.getSize() / 2) + 5),
						showedSelection.getCoords()[1] + showedSelection.getSize() + 5);
			}
		}
	}

	public void init(Placeable selectable) {
		cleanUp();

		showedSelection = selectable;

		JButton helperButton;
		if (selectable.getOrders() != null) {
			for (Orders order : selectable.getOrders()) {
				helperButton = new JButton(order.getImg());
				// helperButton = new JButton(order.name());
				helperButton.addActionListener(this);
				orders.put(helperButton, order);
			}

			System.out.println("Number of orders detected: " + orders.keySet().size());
			for (JButton button : orders.keySet()) {
				add(button);
				revalidate();
			}
		}
	}

	private void cleanUp() {
		showedSelection = null;
		orders = new HashMap<JButton, Orders>();
		removeAll();
	}
}
