package starry;

import java.io.InputStream;
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
import javafx.scene.image.Image;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.html.HTMLInputElement;

import netscape.javascript.JSObject;

public abstract class StarryApp extends Application {

	public WebView page = new WebView();
	
	public Stage mainStage;
	public Scene mainScene;

	public void setAction(String selector, EventListener listener) {
		Document document = page.getEngine().getDocument();
		if (document == null) return;
		
		Element element = document.getElementById(selector);
		if (element == null) return;
		
		EventTarget target = (EventTarget)element;
		target.addEventListener("click", listener, false);
	}
	
	@Override
	public void start(Stage stage) {
		StarryApp.instance = this;
		mainStage = stage;
		var icon = getClass().getResourceAsStream("/icon-black.png");
		mainStage.getIcons().add(new Image(icon));
		
		try {
			page.getEngine().getLoadWorker()
				.stateProperty()
				.addListener((value, last, current) -> {
					if (current == State.SUCCEEDED) {
						initialize();
					}
				});

			mainScene = new Scene(page, 480, 360);
			mainScene.getStylesheets().add("app.css");
			stage.setScene(mainScene);
			mainStage.setMinWidth(480);
			mainStage.setMinHeight(360);

			var location = getClass().getResource("/main.css");
			page.getEngine().setUserStyleSheetLocation(location.toString());
			
			Method main = getClass().getMethod("main");
			if (valid(main)) {
				main.invoke(this);
			}
		} catch (Exception e) { }
		
		stage.show();
	}
	
	public void setTitle(String name) {
		mainStage.setTitle(name);
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
	
	// Load HTML data from file
	public void loadFile(String file) {
		InputStream input = getClass().getResourceAsStream(file);
        
		String buffer = "";
		try {
			while (true) {
				int k = input.read();
				if (k < 0) break;
				buffer += (char)k;
			}
		} catch (Exception e) { }
		loadContent(buffer);
	}
	
	// Forward to getElementById()
	public Element getElement(String identifier) {
		return page.getEngine()
				.getDocument()
				.getElementById(identifier);
	}
	
	// Forward to createElement()
	public Element createElement(String tag) {
		return page.getEngine()
				.getDocument()
				.createElement(tag);
	}
	
	// Forward to executeScript()
	public void executeScript(String script) {
		page.getEngine().executeScript(script);
	}
	
	// Forward to setContextText()
	public void setText(String selector, String text) {
		Platform.runLater( () -> {
			var element = getElement(selector);
			if (element == null) return;
			
			try {
				element.setTextContent(text);
			} catch (Exception e) { }
		});
	}

	// Call application's setup() method
	void initialize() {
		try {
			Method setup = getClass().getMethod("setup");
			if (valid(setup)) {
				setup.invoke(this);
			}
		} catch (Exception e) { }
	}

	// Helper method to prevent using !=
	boolean valid(Object o) {
		return o != null;
	}
	
	
	/* This is not necessary anymore */
	public static StarryApp getInstance() {
		return instance;
	}

	private static StarryApp instance;
}
