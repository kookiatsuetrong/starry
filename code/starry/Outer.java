package starry;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Outer extends JPanel {
	public Outer() {
		super();
		addMouseListener(new MouseClick());
		addMouseMotionListener(new MouseMotion());
		Dimension d = new Dimension(Starry.WIDTH, Starry.HEIGHT);
		setPreferredSize(d);
	}
	
	// java.awt.Color moving  = new java.awt.Color(0,0,0,64); // Windows / macOS
	java.awt.Color moving  = new java.awt.Color(51,51,51);    // Linux
	java.awt.Color display = new java.awt.Color(0xF0, 0xF4, 0xFF, 210);
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		// g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(moving);
		
		Dimension pref = getPreferredSize();
		g2d.fillRoundRect(1, 1, 
				pref.width - 2, pref.height - 2, 
				Starry.OUTER_RADIUS, Starry.OUTER_RADIUS);
	}
}

