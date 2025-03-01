package starry;

import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventType;

import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import netscape.javascript.JSObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLInputElement;

public abstract class StarryApp extends Application {

	public static StarryApp getInstance() {
		return instance;
	}

	static StarryApp instance;

	public WebView page = new WebView();



	public void setAction(String selector, EventListener listener) {
		Document document = page.getEngine()
					.getDocument();
		Element element = document.getElementById(selector);
		EventTarget target = (EventTarget)element;
		target.addEventListener("click", listener, false);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			StarryApp.instance = this;

			ChangeHandler changer = new ChangeHandler();
			page.getEngine().getLoadWorker()
				.stateProperty()
				.addListener(changer);

			VBox box = new VBox(page);
			Scene scene = new Scene(box, 480, 360);
			stage.setScene(scene);
			stage.setTitle("Starry");
			
			Class<?> instance = this.getClass();
			Method main = instance.getMethod("main");		
			if (valid(main)) {
				main.invoke(this);
			}
		
			stage.show();
		} catch (Exception e) { }
	}
	
	boolean valid(Object o) {
		return o != null;
	}
	
	public void load(String location) {
		page.getEngine().load(location);
	}
	
	public void loadContent(String content) {
		page.getEngine().loadContent(content);
	}
	
	public void loadContent(String content, String type) {
		page.getEngine().loadContent(content, type);
	}

	void initialize() {
		
		try {
			Class<?> instance = this.getClass();
			Method setup = instance.getMethod("setup");
			if (valid(setup)) {
				setup.invoke(this);
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
	}

}


class ChangeHandler implements ChangeListener<State> {
	@Override
	public void changed(ObservableValue value,
						State last,
						State current) {
		if (current == State.SUCCEEDED) {
			StarryApp.getInstance().initialize();
		}
	}

	boolean valid(Object o) {
		return o != null;
	}
}


