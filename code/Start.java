import starry.StarryApp;
import javafx.application.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}
	
	public void setup() {
		setAction("love-button", e -> add("Love") );
		setAction("hate-button", e -> add("Hate") );
	}
	
	void add(String message) {
		Document document = page.getEngine().getDocument();
		Element report = document.getElementById("report");
		
		Element item = document.createElement("p");
		try {
			item.setTextContent(message);
			report.appendChild(item);
		} catch (Exception e) { }
	}
	
	String content = 
	"""
	<html>
		<head>
		</head>
		<body>
			<main>
				<button id="love-button">Love</button>
				<button id="hate-button">Hate</button>
				<section id="report"></section>
			</main>
		</body>
		<style>
			#report { margin-top: 1rem; }
		</style>
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
