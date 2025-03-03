package starry;

import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventType;

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
		Document document = page.getEngine().getDocument();
		Element element = document.getElementById(selector);
		EventTarget target = (EventTarget)element;
		target.addEventListener("click", listener, false);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			StarryApp.instance = this;

			page.getEngine().getLoadWorker()
				.stateProperty()
				.addListener((value, last, current) -> {
					if (current == State.SUCCEEDED) {
						initialize();
					}
				});

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
	
	// Forward to engine
	public void load(String location) {
		page.getEngine().load(location);
	}
	
	// Forward to engine
	public void loadContent(String content) {
		page.getEngine().loadContent(content);
	}
	
	// Forward to engine
	public void loadContent(String content, String type) {
		page.getEngine().loadContent(content, type);
	}
	
	// Forward to getElementById()
	public Element getElement(String identifier) {
		return page.getEngine()
				.getDocument()
				.getElementById(identifier);
	}
	
	public Element createElement(String tag) {
		return page.getEngine()
				.getDocument()
				.createElement(tag);
	}

	// Call application's setup() method
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

	// Helper method to prevent using !=
	boolean valid(Object o) {
		return o != null;
	}
}
