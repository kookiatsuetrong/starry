## The Starry Framework

The framework for writing modern Java desktop application.


A simple app.

```java
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		load("https://google.com");
	}

}
```

Compiling

```bash
javac --class-path "code"          \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	code/Start.java
```

Running

```bash
java --class-path "code" \
	--module-path "./javafx/lib" \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	Start
```

Example app with CSS.

```java
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
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

