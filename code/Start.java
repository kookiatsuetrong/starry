import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

import java.io.InputStream;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import starry.StarryApp;
import org.w3c.dom.events.Event;
// import org.w3c.dom.events.MouseEvent;

public class Start {
	public static void main(String[] data) {
		Starry app = new Starry("main.html");
	}
}

class Starry {
	
	WebView page;
	public static int WIDTH  = 480;
	public static int HEIGHT = 360;
	public static int OUTER_RADIUS   = 36;
	public static int WRAPPER_RADIUS = 18;
	public static int WRAPPER_PAD    = 12;
	public static int HTML_PAD       =  6;
	public static JFrame frame;
	public static Outer outer;
	public static Wrapper wrapper;
	public static JFXPanel panel;
	
	public static Starry instance;
	
	Starry(String file) {
		this();
		Platform.runLater( () -> loadPage(file) );
		instance = this;
	}
	
	Starry() {
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new java.awt.Color(0,0,0,0));
		/*
		frame.setShape(new RoundRectangle2D
				.Double(0, 0, 
						WIDTH, HEIGHT,
						OUTER_RADIUS, OUTER_RADIUS));
		*/
		frame.setSize(WIDTH, HEIGHT);
		outer = new Outer();
		outer.setLayout(null);
		frame.setContentPane(outer);
		
		Dimension wd = new Dimension(
							WIDTH  - 2 * WRAPPER_PAD,
							HEIGHT - 2 * WRAPPER_PAD);
		wrapper = new Wrapper();
		wrapper.setLayout(null);
		wrapper.setBounds(WRAPPER_PAD, WRAPPER_PAD,
						wd.width, wd.height);
		
		Dimension pd = new Dimension(
						WIDTH  - 2 * (WRAPPER_PAD + HTML_PAD),
						HEIGHT - 2 * (WRAPPER_PAD + HTML_PAD) );
		panel = new JFXPanel();
		panel.setPreferredSize(pd);
		panel.setBounds(HTML_PAD, HTML_PAD, pd.width, pd.height);
		wrapper.add(panel);
		
		frame.getContentPane().add(wrapper);
		frame.pack();
		
		var toolkit = Toolkit.getDefaultToolkit();
		var size = toolkit.getScreenSize();
		frame.setLocation(
			(size.width  - WIDTH ) / 2,
			(size.height - HEIGHT) / 2);
		frame.setVisible(true);
		
		Platform.runLater(() -> createApp(panel));
	}
	
	void resizeBy(int w, int h) {
		Dimension pd = panel.getPreferredSize();
		pd.width  += w;
		pd.height += h;
		panel.setBounds(HTML_PAD, HTML_PAD, 
				pd.width, pd.height);
		panel.setPreferredSize(pd);
		
		Dimension wd = wrapper.getPreferredSize();
		wd.width  += w;
		wd.height += h;
		wrapper.setPreferredSize(wd);
		wrapper.setBounds(WRAPPER_PAD, WRAPPER_PAD, 
					wd.width, wd.height);
		
		Dimension od = outer.getPreferredSize();
		od.width  += w;
		od.height += h;
		outer.setPreferredSize(od);
		
		frame.setPreferredSize(od);
		frame.pack();
	}
	
	void createApp(JFXPanel panel) {
		page = new WebView();
		Scene scene = new Scene(page);
		scene.getStylesheets().add("app.css");
		panel.setScene(scene);
	}
	
	void loadPage(String file) {
		loadFile(file);
		var mainStyle = getClass().getResource("main.css");
		page.getEngine().setUserStyleSheetLocation
							(mainStyle.toString());
	}
	
	public void loadContent(String content) {
		page.getEngine().loadContent(content);
	}

	public void loadFile(String file) {
		InputStream input = getClass().getResourceAsStream(file);
        
		String buffer = "";
		try {
			while (true) {
				int k = input.read();
				if (k < 0) break;
				buffer += (char)k;
			}
		} catch (Exception e) { }
		loadContent(buffer);
	}
	
}

class Wrapper extends JPanel {
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

class Outer extends JPanel {
	Outer() {
		super();
		addMouseListener(new MouseClick());
		addMouseMotionListener(new MouseMotion());
		Dimension d = new Dimension(Starry.WIDTH, Starry.HEIGHT);
		setPreferredSize(d);
	}
	
	java.awt.Color moving  = new java.awt.Color(0,0,0,64);
	java.awt.Color display = new java.awt.Color(0xF0, 0xF4, 0xFF, 210);
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
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

enum BorderEdge { TOP, RIGHT, BOTTOM, LEFT }

class MouseMotion extends MouseMotionAdapter {
	
	public static Point start;
	public static BorderEdge edge;
	
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		if (start == null) return;
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		
		// For moving
		if (edge == BorderEdge.TOP) {
			Starry.frame.setLocation(x - start.x, y - start.y);
		}
		
		// For resizing
		if (edge == BorderEdge.RIGHT) {
			Point p = Starry.frame.getLocation();
			Dimension d = Starry.outer.getPreferredSize();
			int tx = x - p.x - d.width;
			Starry.instance.resizeBy(tx, 0);
		}
		
		// For resizing
		if (edge == BorderEdge.BOTTOM) {
			Point p = Starry.frame.getLocation();
			Dimension d = Starry.outer.getPreferredSize();
			int ty = y - p.y - d.height;
			Starry.instance.resizeBy(0, ty);
		}
		
		// For resizing
		if (edge == BorderEdge.LEFT) {
			Point p = Starry.frame.getLocation();
			Starry.frame.setLocation(x - start.x, p.y);
			int tx = x - start.x - p.x;
			Starry.instance.resizeBy(-tx, 0);
		}	
	}
}

class MouseClick extends MouseAdapter {
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		
		Point     framePoint = Starry.frame.getLocationOnScreen();
		Dimension frameSize  = Starry.outer.getPreferredSize();
		
		int dx = x - framePoint.x;
		int dy = y - framePoint.y;
		
		MouseMotion.start = new Point(dx, dy);
		
		if (framePoint.y <= y && y <= framePoint.y + Starry.WRAPPER_PAD) {
			MouseMotion.edge = BorderEdge.TOP;
			Component c = (Component)Starry.frame;
			c.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			return;
		}
		
		if (framePoint.y + frameSize.height - Starry.WRAPPER_PAD <= y && 
			y <= framePoint.y + frameSize.height) {
			MouseMotion.edge = BorderEdge.BOTTOM;
			Component c = (Component)Starry.frame;
			c.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			return;
		}
		
		if (framePoint.x <= x && x <= framePoint.x + Starry.WRAPPER_PAD) {
			MouseMotion.edge = BorderEdge.LEFT;
			return;
		}
		
		if (framePoint.x + frameSize.width - Starry.WRAPPER_PAD <= x &&
			x <= framePoint.x + frameSize.width) {
			MouseMotion.edge = BorderEdge.RIGHT;
			return;
		}
		
		System.out.println("Unknown Edge");
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		MouseMotion.start = null;
		Component c = (Component)Starry.frame;
		c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
}


/*

public class Start extends StarryApp {

	public static void main(String[] data) {
		SwingApp a = new SwingApp();
	}
	
	public void main() {
		setTitle("Application");
		loadFile("main.html");
	}
	
	public void setup() {
		setAction("refresh", e -> main() );
		setAction("love-button", Start::showLove);
		
		String arch = System.getProperty("os.arch");
		System.out.println(arch);
		
		String os = System.getProperty("os.name");
		System.out.println(os);
		
		setText("report-arch", arch);
		setText("report-os", os);
	}
	
	static void showLove(Event e) {
		System.out.println(e);
	}
}

*/