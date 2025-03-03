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
	<body>
		<input id="task" />
		<button id="add-button">Add</button>
		<section id="report"></section>
	</body>
	<style>
	* {
		outline: none;
		font-family: sans-serif;
	}
	body {
	 	padding: 1rem;
	}
 	button {
		color: white;
		background: dodgerblue;
	 	padding: .5rem 1rem;
		border: none;
		border-radius: .35rem;
		transition: background .25s linear;
	}
	button:hover {
	 	background: steelblue;
	}
	input {
		border: none;
		border-radius: .35rem;
		height: 2rem;
		padding: .25rem .5rem;
		background: #eee;
	}
	</style>
	""";
}

