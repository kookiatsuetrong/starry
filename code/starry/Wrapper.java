package starry;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Wrapper extends JPanel {
	public Wrapper() {
		super();
		Dimension d = new Dimension(
				Starry.WIDTH  - 2 * Starry.WRAPPER_PAD,
				Starry.HEIGHT - 2 * Starry.WRAPPER_PAD);
		setPreferredSize(d);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new java.awt.Color(0xF0, 0xF4, 0xFF) );
		// g2d.setColor(java.awt.Color.GRAY);
		Dimension d = getPreferredSize();
		
		g2d.fillRoundRect(1, 1, d.width - 1, d.height - 1,
				Starry.WRAPPER_RADIUS, Starry.WRAPPER_RADIUS);
	}
}
