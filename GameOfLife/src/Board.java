import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.lang.Math;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */

public class Board extends JComponent implements MouseInputListener, ComponentListener {
	private static final long serialVersionUID = 1L;
	private Point[][] points = new Point[][]{};
	private int size = 14;
	public boolean ifRain = false;

	public Board(int length, int height) {
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		setOpaque(true);
	}

	// single iteration
	public void iteration() {
		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y) {
				if (ifRain)
					points[x][y].calculateNewStateRain();
				else
					points[x][y].calculateNewState();
			}

		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y].changeState();

		if (ifRain) {
			for (int x = 0; x < points.length; x++)
				points[x][0].drop();
		}

		this.repaint();
	}

	// clearing board
	public void clear() {
		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y) {
				points[x][y].setState(0);
			}
		this.repaint();
	}

	private void initialize(int length, int height) {
		points = new Point[length][height];

		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y] = new Point();

		for (int x = 0; x < points.length; ++x) {
			for (int y = 0; y < points[x].length; ++y) {
				//TODO: initialize the neighborhood of points[x][y] cell
				points[x][y].addNeighbor(points[Math.max(0, x - 1)][Math.max(0, y - 1)]);
				points[x][y].addNeighbor(points[Math.max(0, x - 1)][y]);
				points[x][y].addNeighbor(points[Math.max(0, x - 1)][Math.min(height - 1, y + 1)]);
				points[x][y].addNeighbor(points[Math.min(length - 1, x + 1)][Math.max(0, y - 1)]);
				points[x][y].addNeighbor(points[Math.min(length - 1, x + 1)][y]);
				points[x][y].addNeighbor(points[Math.min(length - 1, x + 1)][Math.min(height - 1, y + 1)]);
				points[x][y].addNeighbor(points[x][Math.min(height - 1, y + 1)]);
				points[x][y].addNeighbor(points[x][Math.max(0, y - 1)]);
			}
		}
	}

	private void initializeRain(int length, int height) {
		points = new Point[length][height];

		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y] = new Point();

		for (int x = 0; x < points.length; ++x) {
			for (int y = 0; y < points[x].length; ++y) {
				//TODO: initialize the neighborhood of points[x][y] cell
				if(y - 1 >= 0) points[x][y].addNeighbor(points[x][y - 1]);
			}
		}
	}

	//paint background and separators between cells
	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.GRAY);
		drawNetting(g, size);
	}

	// draws the background netting
	private void drawNetting(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = this.getWidth() - insets.right;
		int lastY = this.getHeight() - insets.bottom;

		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}

		for (x = 0; x < points.length; ++x) {
			for (y = 0; y < points[x].length; ++y) {
				if (points[x][y].getState() != 0) {
					switch (points[x][y].getState()) {
					case 1:
						g.setColor(new Color(0x0000ff));
						break;
					case 2:
						g.setColor(new Color(0xFF1B1BFF, true));
						break;
					case 3:
						g.setColor(new Color(0xFF2448FF, true));
						break;						
					case 4:
						g.setColor(new Color(0x1460EF));
						break;						
					case 5:
						g.setColor(new Color(0x82C7EE));
						break;						
					case 6:
						g.setColor(new Color(0xffffff));
						break;						
					}
					g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
				}
			}
		}

	}

	public void initializeNew(){
		if (ifRain) initializeRain(getWidth(), getHeight());
		else initialize(getWidth(), getHeight());
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			points[x][y].clicked();
			this.repaint();
		}
	}

	public void componentResized(ComponentEvent e) {
		int dlugosc = (this.getWidth() / size) + 1;
		int wysokosc = (this.getHeight() / size) + 1;
		initialize(dlugosc, wysokosc);
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			points[x][y].setState(1);
			this.repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

}
