import starry.StarryApp;
import java.io.FileReader;

public class Start extends StarryApp {
	
	public void main() {
		String buffer = "";
		try {		
			FileReader fr = new FileReader("code/main.html");
			while (true) {
				int k = fr.read();
				if (k < 0) break;
				buffer += (char)k;
			}
		} catch (Exception e) { }
		loadContent(buffer);
	}
	
	public void setup() {
		setAction("refresh", e -> main() );
	}
}
