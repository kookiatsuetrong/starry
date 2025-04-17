package starry;

import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class Starry {
	
	WebView page;
	public static int WIDTH  = 480;
	public static int HEIGHT = 360;
	public static int OUTER_RADIUS   = 36;
	public static int WRAPPER_RADIUS = 18;
	public static int WRAPPER_PAD    = 12;
	public static int HTML_PAD       =  6;
	public static CustomFrame frame;
	public static Outer outer;
	public static Wrapper wrapper;
	public static JFXPanel panel;
	
	public static Starry instance;
	
	String operatingSystem = "";
	
	public Starry() {
		instance = this;
		
		operatingSystem = System.getProperty("os.name");
		
		frame = new CustomFrame();
		
		ArrayList<Image> list = new ArrayList<>();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		String[] icons = {"icon-128.png",
							"icon-64.png",
							"icon-32.png",
							"icon-24.png",
							"icon-16.png" };
		for (String icon : icons) {
			URL resource = getClass().getClassLoader()
							.getResource("starry/" + icon);
			Image image = toolkit.getImage(resource);
			list.add(image);
		}
		frame.setIconImages(list);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		Dimension fd = new Dimension(WIDTH, HEIGHT);
		frame.setPreferredSize(fd);
		frame.setSize(fd);
		frame.setOpacity(0.25f);
		outer = new Outer();
		outer.setLayout(null);
		frame.setContentPane(outer);
		
		// For Linux
		if (operatingSystem.equals("Linux")) {
			frame.setShape(new RoundRectangle2D
					.Double(1, 1, 
							WIDTH - 2, HEIGHT - 2,
							OUTER_RADIUS, OUTER_RADIUS));
			
			frame.setBackground(new java.awt.Color(0,0,0));
		}
		
		// For Windows / macOS
		if (operatingSystem.equals("Linux") == false) {
			frame.setShape(new RoundRectangle2D
				.Double(0, 0,
						WIDTH, HEIGHT,
						OUTER_RADIUS, OUTER_RADIUS));
			frame.setBackground(new java.awt.Color(0,0,0,0));
		}
		
		Dimension wd = new Dimension(
							WIDTH  - 2 * WRAPPER_PAD,
							HEIGHT - 2 * WRAPPER_PAD);
		wrapper = new Wrapper();
		wrapper.setLayout(null);
		wrapper.setPreferredSize(wd);
		wrapper.setBounds(WRAPPER_PAD, WRAPPER_PAD,
						wd.width, wd.height);
		
		Dimension pd = new Dimension(
						WIDTH  - 2 * (WRAPPER_PAD + HTML_PAD),
						HEIGHT - 2 * (WRAPPER_PAD + HTML_PAD) );
		panel = new JFXPanel();
		panel.setPreferredSize(pd);
		panel.setBounds(HTML_PAD, HTML_PAD, pd.width, pd.height);
		wrapper.add(panel);
		
		outer.add(wrapper);
		frame.pack();
		
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(
			(size.width  - WIDTH ) / 2,
			(size.height - HEIGHT) / 2);
		frame.setVisible(true);
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
		
		// For Ubuntu / GNOME
		if (operatingSystem.equals("Linux")) {
			frame.setShape(new RoundRectangle2D
				.Double(1, 1, 
					od.width-2, od.height-2,
					OUTER_RADIUS, OUTER_RADIUS));
		}
		
		// For Windows / macOS
		if (operatingSystem.equals("Linux") == false) {
			frame.setShape(new RoundRectangle2D
				.Double(0, 0, 
					od.width, od.height,
					OUTER_RADIUS, OUTER_RADIUS));
		}
		
		SwingUtilities.invokeLater( () -> {
			frame.pack();
			frame.revalidate();
		});
	}
	
	void createApp(JFXPanel panel) {
		page = new WebView();
		Scene scene = new Scene(page);
		scene.getStylesheets().add("starry/app.css");
		panel.setScene(scene);
	}
	
	ReadyFunction readyFunction;
	
	public void whenReady(ReadyFunction r) {
		readyFunction = r;
	}
	
	void setup() {
		page.getEngine()
			.getLoadWorker()
			.stateProperty()
			.addListener((value, last, current) -> {
				if (current == Worker.State.SUCCEEDED) {
					System.out.println("ready");
					if (readyFunction == null) return;
					readyFunction.run();
				}
			});
	}
	
	/**
	 * Forwards to page.getEngine().loadContent()
	 * 
	 */
	public void loadString(String content) {
		Platform.runLater(() -> {
			createApp(panel);
			page.getEngine().loadContent(content);
			URL url = getClass().getClassLoader().getResource("main.css");
			page.getEngine().setUserStyleSheetLocation(url.toString());
			setup();
		});
	}

	/**
	 * Reads file content from resource, and display as the main view.
	 * 
	 */
	public void loadFile(String file) {
		InputStream input = getClass()
							.getClassLoader()
							.getResourceAsStream(file);
        
		String buffer = "";
		try {
			while (true) {
				int k = input.read();
				if (k < 0) break;
				buffer += (char)k;
			}
		} catch (Exception e) { }
		loadString(buffer);
	}
	
	/**
	 * Forwards to page.getEngine().load()
	 * 
	 */
	public void loadURL(String location) {
		Platform.runLater(() -> {
			createApp(panel);
			page.getEngine().load(location);
			setup();		
		});
	}
	
	/**
	 * A helper method to add the "click" event handler
	 */
	public void setAction(String selector, EventListener listener) {
		Document document = page.getEngine().getDocument();
		if (document == null) return;
		
		Element element = document.getElementById(selector);
		if (element == null) return;
		
		EventTarget target = (EventTarget)element;
		target.addEventListener("click", listener, false);
	}
	
	/**
	 * Forwards to getElementById()
	 * @param identifier
	 * @return 
	 */
	public Element getElement(String identifier) {
		return page.getEngine()
				.getDocument()
				.getElementById(identifier);
	}
	
	/**
	 * Forwards to document.createElement()
	 * 
	 */
	public Element createElement(String tag) {
		return page.getEngine()
				.getDocument()
				.createElement(tag);
	}
	
	/**
	 * Forwards to executeScript()
	 * 
	 */
	public void executeScript(String script) {
		page.getEngine().executeScript(script);
	}
	
	/**
	 * Forwards to setTextContent()
	 * 
	 */
	public void setText(String selector, String text) {
		Platform.runLater( () -> {
			var element = getElement(selector);
			if (element == null) return;
			
			try {
				element.setTextContent(text);
			} catch (Exception e) { }
		});
	}

	/**
	 * Helper method to prevent using !=
	 * @param o
	 * @return 
	 */
	boolean valid(Object o) {
		return o != null;
	}
	
}

class CustomFrame extends JFrame {

	java.awt.Color color = new java.awt.Color(0,0,0,128);   // Windows / macOS
	
	/*
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		
		Dimension pref = getPreferredSize();
		g2d.fillRoundRect(1, 1, 
				pref.width - 2, pref.height - 2, 
				Starry.OUTER_RADIUS, Starry.OUTER_RADIUS);
	}
	*/
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
		
		if (framePoint.y + frameSize.height - 2 * Starry.WRAPPER_PAD <= y && 
			y <= framePoint.y + frameSize.height) {
			MouseMotion.edge = BorderEdge.BOTTOM;
			Component c = (Component)Starry.frame;
			c.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			return;
		}
		
		if (framePoint.x <= x && x <= framePoint.x + Starry.WRAPPER_PAD) {
			MouseMotion.edge = BorderEdge.LEFT;
			Component c = (Component)Starry.frame;
			c.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			return;
		}
		
		if (framePoint.x + frameSize.width - 2 * Starry.WRAPPER_PAD <= x &&
			x <= framePoint.x + frameSize.width) {
			MouseMotion.edge = BorderEdge.RIGHT;
			Component c = (Component)Starry.frame;
			c.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			return;
		}
		
		System.out.println("Unknown Edge");
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		MouseMotion.start = null;
		Component c = (Component)Starry.frame;
		c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Starry.instance.resizeBy(0,0);
	}
	
}
