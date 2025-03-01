package starry;

import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public abstract class StarryApp extends Application {
	WebView page = new WebView();
	
	@Override
	public void start(Stage stage) throws Exception {

		Class<?> instance = this.getClass();
		Method main = instance.getMethod("main");		
		if (valid(main)) {
			main.invoke(this);
		}
		
		VBox box = new VBox(page);
		Scene scene = new Scene(box, 480, 360);
		stage.setScene(scene);
		stage.setTitle("Starry");
		stage.show();
	}
	
	boolean valid(Object o) {
		return o != null;
	}
	
	public void load(String location) {
		page.getEngine().load(location);
	}
	
	// abstract void main();
}
