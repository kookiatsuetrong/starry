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
		VBox box = new VBox(page);
		Scene scene = new Scene(box, 960, 600);
		stage.setScene(scene);
		stage.setTitle("Title");
		stage.show();

		Class<?> c = this.getClass();
		Method main = c.getMethod("main");		
		if (valid(main)) {
			main.invoke(this);
		}
	}
	
	boolean valid(Object o) {
		return o == null ? false : true;
	}
	
	void go(String location) {
		page.getEngine().load(location);
	}
	
	// abstract void main();
}
