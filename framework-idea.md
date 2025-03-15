Switching Page
```java
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

}
```

Modal Dialog
```java
	void showModalDialog() {
		WebView view = new WebView();
		VBox box = new VBox(view);
		Scene scene = new Scene(box, 360, 240);
		Stage dialog = new Stage();
		dialog.setScene(scene);
		dialog.initOwner(mainStage);
		dialog.initStyle(StageStyle.UNDECORATED);
		
		dialog.setIconified(false);
		dialog.setMaximized(false);
		dialog.setFullScreen(false);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.show();
		dialog.showAndWait();
	}
```


