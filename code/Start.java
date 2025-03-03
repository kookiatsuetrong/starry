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
	<body>
		<button id="sample-button">Exit</button>
	</body>
	<style>
		* {
			font-family: sans-serif;
			outline: none;
		}
		button {
			font-size: 1rem;
			border: none;
			border-radius: 1rem;
			padding: .5rem 1rem;
			background: dodgerblue;
			color: white;
		}
	</style>
	""";
}
