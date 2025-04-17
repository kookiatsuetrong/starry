import starry.Starry;
import org.w3c.dom.events.Event;

public class Start {
	public static void main(String[] data) {
		Starry app = new Starry();
		app.loadFile("main.html");
		app.whenReady( () -> setup(app) );
	}

	static void setup(Starry app) {
		app.setAction("refresh", Start::showRefresh);
		app.setAction("love-button", e -> System.out.println("Love") );
		app.setAction("hate-button", e -> System.out.println("Hate") );

		String arch = System.getProperty("os.arch");
		System.out.println(arch);

		String os = System.getProperty("os.name");
		System.out.println(os);

		app.setText("report-arch", arch);
		app.setText("report-os", os);
	}
	
	static void showRefresh(Event e) {
		System.out.println("Refreshing");
	}
}
