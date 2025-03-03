
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import starry.StarryApp;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.html.HTMLInputElement;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(mainPage + style);
	}
	
	public void setup() {
		setAction("settings-button", e -> showSettings() );
	}
	void showSettings() {
		page.getEngine().loadContent(settingsPage + style);
		setAction("home-button", e -> showHome() );
	}
	
	void showHome() {
		page.getEngine().loadContent(mainPage + style);
		setAction("settings-button", e -> showSettings() );
	}
	
String mainPage = 
"""
<body>
	<button id="settings-button">Settings</button>
</body>
""";

String settingsPage = 
"""
<body>
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

	void showModalDialog() {
		WebView view = new WebView();
		VBox box = new VBox(view);
		Scene scene = new Scene(box, 360, 240);
		Stage dialog = new Stage();
		dialog.setScene(scene);
		dialog.initOwner(mainStage);
		dialog.initStyle(StageStyle.UNDECORATED);
		/*
		dialog.setIconified(false);
		dialog.setMaximized(false);
		dialog.setFullScreen(false);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.show();
		*/
		dialog.showAndWait();
	}
}

