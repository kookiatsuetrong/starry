import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.InputStream;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import starry.StarryApp;
import org.w3c.dom.events.Event;

public class Start {
	public static void main(String[] data) {
		Starry app = new Starry("main.html");
	}
}

class Starry {
	
	WebView page;
	public static int WIDTH  = 480;
	public static int HEIGHT = 360;
	
	Starry(String file) {
		this();
		Platform.runLater( () -> loadPage(file) );
	}
	
	Starry() {
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new java.awt.Color(0,0,0,0));
		frame.setShape(new RoundRectangle2D
				.Double(0, 0, WIDTH, HEIGHT, 25, 25));
		frame.setContentPane(new Outer());
		
		var toolkit = Toolkit.getDefaultToolkit();
		var size = toolkit.getScreenSize();
		frame.setLocation(
			(size.width  - WIDTH ) / 2,
			(size.height - HEIGHT) / 2);
		frame.setSize(WIDTH, HEIGHT);
		
		frame.getContentPane().setLayout(null);
		RoundRectangle rr = new RoundRectangle();
		rr.setBounds(10, 10, WIDTH - 20, HEIGHT - 20);
		rr.setLayout(null);
		
		JFXPanel panel = new JFXPanel();
		panel.setBounds(6,6, WIDTH - 32, HEIGHT - 32);
		rr.add(panel);
		
		frame.getContentPane().add(rr);
		
		frame.setVisible(true);
		
		Platform.runLater(() -> createApp(panel));
	}
	
	void createApp(JFXPanel panel) {
		page = new WebView();
		Scene scene = new Scene(page);
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

class RoundRectangle extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new java.awt.Color(0xF0, 0xF4, 0xFF) );
		g2d.fillRoundRect(1, 1, Starry.WIDTH-22, Starry.HEIGHT-22, 14, 14);
	}
}

class Outer extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(new java.awt.Color(0,0,0,0));
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new java.awt.Color(0xF0, 0xF4, 0xFF, 220) );
		g2d.fillRoundRect(1, 1, Starry.WIDTH-2, Starry.HEIGHT-2, 25, 25);
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