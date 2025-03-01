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

/*
javac --class-path "code"          \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	code/Start.java
	
	
java --class-path "code" \
	--module-path "./javafx/lib" \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	Start

*/
