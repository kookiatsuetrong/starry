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

Sample app with event handling and CSS.

```java
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}

	public void setup() {
		setAction("love-button", e -> System.out.println("Love") );
		setAction("hate-button", e -> System.out.println("Hate") );
	}
	
	String content = 
	"""
	<body>
		<button id="love-button">Love</button>
		<button id="hate-button">Hate</button>
	</body>
	<style>
	body {
		font-family: sans-serif;
	 	padding: 1rem;
	}
 	button {
		font-family: sans-serif;
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

