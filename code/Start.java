import starry.StarryApp;
import org.w3c.dom.events.Event;

public class Start extends StarryApp {
	
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

