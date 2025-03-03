
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(mainPage + style);
	}
	
	public void setup() {
		setAction("settings-button", e -> showSettings() );
		setAction("home-button", e -> showHome() );
	}
	
	void showSettings() {
		loadContent(settingsPage + style);
	}
	
	void showHome() {
		loadContent(mainPage + style);
	}
	
String mainPage = 
"""
<body class="white">
	<button id="settings-button">Settings</button>
</body>
""";

String settingsPage = 
"""
<body class="light">
	<button id="home-button">Home</button>
</body>
""";

String style = 
"""
<style>
* {
	outline: none;
	font-family: sans-serif;
}
body {
	padding: 1rem;
}
body.white {
	background: white;
}
body.light {
	background: #f0f4ff;
}
button {
	color: white;
	background: dodgerblue;
	padding: .5rem 1rem;
	border: none;
	border-radius: .35rem;
	transition: background .25s linear;
	font-size: 1rem;
}
button:hover {
	background: steelblue;
}
input {
	border: none;
	border-radius: .35rem;
	padding: .5rem .5rem;
	background: #eee;
	font-size: 1rem;
}
</style>
""";

}

