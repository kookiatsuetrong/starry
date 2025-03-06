import starry.StarryApp;
import javafx.application.Platform;
import org.w3c.dom.events.Event;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}
	
	public void setup() {
		setAction("sample-button", e -> exit(e) );
	}
	
	void exit(Event event) {
		Platform.exit();
	}
	
	String content = 
	"""
	<html>
		<head>
		</head>
		<body>
			<main>
				<button id="sample-button">Exit</button>
			</main>
		</body>
	</html>
	""";
}

/*
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		loadFile("code/main.html");
	}
	
	public void setup() {
		setAction("refresh", e -> main() );
		
		String arch = System.getProperty("os.arch");
		System.out.println(arch);
		
		
		String os = System.getProperty("os.name");
		System.out.println(os);
		
		try {
			getElement("report-arch")
				.setTextContent(arch);
			getElement("report-os")
				.setTextContent(os);
		} catch (Exception e) { }
	}
}
*/
