import starry.Starry;

public class Start {
	public static void main(String[] data) {
		new Start().reload();
	}
	
	Starry app = new Starry();
	
	void reload() {
		app.loadFile("main.html");
		app.whenReady( () -> setup() );
	}

	void setup() {
		app.setAction("refresh",     e -> reload() );
		app.setAction("love-button", e -> System.out.println("Love") );
		app.setAction("hate-button", e -> System.out.println("Hate") );

		String arch = System.getProperty("os.arch");
		System.out.println(arch);

		String os = System.getProperty("os.name");
		System.out.println(os);

		app.setText("report-arch", arch);
		app.setText("report-os", os);
	}
}

