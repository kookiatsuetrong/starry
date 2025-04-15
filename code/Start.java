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
	
	Starry(String file) {
		this();
		Platform.runLater( () -> loadPage(file) );
	}
	
	Starry() {
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new java.awt.Color(0,0,0,0));
		frame.setShape(new RoundRectangle2D
				.Double(0, 0, 
						WIDTH, HEIGHT,
						OUTER_RADIUS, OUTER_RADIUS));
		outer = new Outer();
		outer.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setContentPane(outer);
		
		var toolkit = Toolkit.getDefaultToolkit();
		var size = toolkit.getScreenSize();
		frame.setLocation(
			(size.width  - WIDTH ) / 2,
			(size.height - HEIGHT) / 2);
		frame.setSize(WIDTH, HEIGHT);
		
		frame.getContentPane().setLayout(null);
		
		Wrapper wrapper = new Wrapper();
		wrapper.setBounds(WRAPPER_PAD, WRAPPER_PAD, 
				WIDTH  - 2 * WRAPPER_PAD, 
				HEIGHT - 2 * WRAPPER_PAD);
		wrapper.setLayout(null);
		JFXPanel panel = new JFXPanel();
		panel.setBounds(HTML_PAD, HTML_PAD, 
				WIDTH  - 2 * (WRAPPER_PAD + HTML_PAD), 
				HEIGHT - 2 * (WRAPPER_PAD + HTML_PAD));
		wrapper.add(panel);
		frame.getContentPane().add(wrapper);
		
		frame.pack();
		frame.setVisible(true);
		
		Platform.runLater(() -> createApp(panel));
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
		
		/*
		page.getEngine().setUserStyleSheetLocation
							("file:./code/main.css");
		*/
	}
	
	/**
	 * Forwards to page.getEngine().load()
	 * 
	 */
	public void load(String location) {
		page.getEngine().load(location);
	}
	
	/**
	 * Forwards to page.getEngine().loadContent()
	 * 
	 */
	public void loadContent(String content) {
		page.getEngine().loadContent(content);
	}
	
	/**
	 * Forwards to page.getEngine().loadContent()
	 * 
	 */
	public void loadContent(String content, String type) {
		page.getEngine().loadContent(content, type);
	}
	
	/**
	 * Reads file content from resource, and display as the main view.
	 * 
	 */
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
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new java.awt.Color(0xF0, 0xF4, 0xFF) );
		// g2d.setColor(java.awt.Color.GRAY);
		int w = Starry.WIDTH  - 2 * Starry.WRAPPER_PAD - 2;
		int h = Starry.HEIGHT - 2 * Starry.WRAPPER_PAD - 2;
		
		g2d.fillRoundRect(1, 1, w, h, 
				Starry.WRAPPER_RADIUS, Starry.WRAPPER_RADIUS);
	}
}

class Outer extends JPanel {
	Outer() {
		super();
		addMouseListener(new MouseClick());
		addMouseMotionListener(new MouseMotion());
	}
	
	java.awt.Color moving  = new java.awt.Color(0,0,0,210);
	java.awt.Color display = new java.awt.Color(0xF0, 0xF4, 0xFF, 210);
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor( MouseMotion.start == null ? display : moving );
		g2d.fillRoundRect(1, 1, 
				Starry.WIDTH - 2, Starry.HEIGHT - 2, 
				Starry.OUTER_RADIUS, Starry.OUTER_RADIUS);
	}
	
	@Override
	public Dimension getPreferredSize() {
		if (isPreferredSizeSet()) {
			return super.getPreferredSize();            
		}
		return new Dimension(Starry.WIDTH, Starry.HEIGHT);
	}
}

class MouseMotion extends MouseMotionAdapter {
	
	public static Point start;
	
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		if (start == null) return;
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		Starry.frame.setLocation(x - start.x, y - start.y);
	}

}

class MouseClick extends MouseAdapter {
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		
		Point framePoint = Starry.frame.getLocationOnScreen();
		int dx = x - framePoint.x;
		int dy = y - framePoint.y;
		
		MouseMotion.start = new Point(dx, dy);
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		MouseMotion.start = null;
		
		/*
		System.out.println(e);
		SwingUtilities.invokeLater( () -> {
			Dimension s = Starry.frame.getSize();
			Starry.outer.setPreferredSize
						(new Dimension(s.width + 10, s.height));
			Starry.frame.setPreferredSize
						(new Dimension(s.width + 10, s.height));
			Starry.frame.invalidate();
			Starry.frame.pack();
		}); */
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