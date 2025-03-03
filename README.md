## The Starry Framework

The framework for writing modern Java desktop application.


Simple App

```java
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		load("https://google.com");
	}

}
```

Unix BASH commands

```bash
javac --class-path "code"      \
--module-path "./javafx/lib"   \
--add-modules javafx.controls  \
--add-modules javafx.web       \
code/Start.java

java --class-path "code"       \
--module-path "./javafx/lib"   \
--add-modules javafx.controls  \
--add-modules javafx.web       \
Start
```

Windows commands
```
javac --class-path "code"      ^
--module-path "javafx\lib"     ^
--add-modules javafx.controls  ^
--add-modules javafx.web       ^
code\Start.java

java --class-path "code"       ^
--module-path "javafx\lib"     ^
--add-modules javafx.controls  ^
--add-modules javafx.web       ^
Start
```

Event Handling

```java
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
	""";
}
```

Toggle Button

```java
import starry.StarryApp;
import javafx.application.Platform;
import org.w3c.dom.events.Event;
import org.w3c.dom.Element;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}
	
	public void setup() {
		setAction("sample-button", e -> change(e) );
	}
	
	void change(Event event) {
		Element element = (Element)event.getTarget();
		try {
			String current = element.getTextContent();
			String next = "Yes".equals(current) ? "No" : "Yes";
			element.setTextContent(next);
		} catch (Exception e) { }
	}
	
	String content = 
	"""
	<body>
		<button id="sample-button">Yes</button>
	</body>
	""";
}
```

Love Hate

```java
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
	<body>
		<button id="love-button">Love</button>
		<button id="hate-button">Hate</button>
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
	</style>
	""";
}
```

Todo Application
```java
import starry.StarryApp;
import javafx.application.Platform;
import org.w3c.dom.Document;
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
		Document document = page.getEngine().getDocument();
		HTMLInputElement input = (HTMLInputElement)document
								.getElementById("task");
		Element report = document.getElementById("report");
		Element item = document.createElement("p");
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
```