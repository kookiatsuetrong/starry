import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

abstract class StarryApp extends Application {
	WebView page = new WebView();
	
	@Override
	public void start(Stage stage) throws Exception {

		Class<?> cls = this.getClass();
		Method main = cls.getMethod("main");		
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
	
	void go(String location) {
		page.getEngine().load(location);
	}
	
	// abstract void main();
}
