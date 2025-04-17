package starry;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Outer extends JComponent {
	public Outer() {
		super();
		addMouseListener(new MouseClick());
		addMouseMotionListener(new MouseMotion());
		Dimension d = new Dimension(Starry.WIDTH, Starry.HEIGHT);
		setPreferredSize(d);
	}
	
	java.awt.Color color = new java.awt.Color(0,0,0,128);
	java.awt.Color colorLinux = new java.awt.Color(196,196,196);
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

		if (Starry.operatingSystem.equals("Linux")) {
			g2d.setColor(colorLinux);
		}
		if (Starry.operatingSystem.equals("Linux") == false) {
			g2d.setBackground(new java.awt.Color(0,0,0,0));
			g2d.setColor(color);
		}
		
		// g2d.setPaint(new java.awt.Color(0,0,0,0));
	
		Dimension pref = getPreferredSize();
		g2d.fillRoundRect(1, 1, 
				pref.width - 2, pref.height - 2, 
				Starry.OUTER_RADIUS, Starry.OUTER_RADIUS);
	}
}

