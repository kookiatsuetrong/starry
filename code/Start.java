import starry.StarryApp;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.html.HTMLInputElement;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}
	
	public void setup() {
		setAction("add-button", e -> add() );
	}
	
	void add() {
		var input  = (HTMLInputElement)getElement("task");
		var report = getElement("report");
		var item   = createElement("p");
		try {
			item.setTextContent(input.getValue());
			report.appendChild(item);
		} catch (Exception e) { }
		input.setValue("");
	}
	
	String content = 
	"""
	<html>
		<head>
		</head>
		<body>
			<main>
            	<input id="task" placeholder="Task" />
            	<button id="add-button">Add</button>
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
