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
	
	@Override
	public void start(Stage stage) {
		StarryApp.instance = this;
		mainStage = stage;
		
		var icon = getClass().getResourceAsStream("icon-black.png");
		mainStage.getIcons().add(new Image(icon));
		
		mainScene = new Scene(page, 480, 360);
		mainScene.getStylesheets().add("app.css");
		stage.setScene(mainScene);
		/*
		mainStage.setMinWidth(480);
		mainStage.setMinHeight(360);
		*/
		mainStage.show();

		var mainStyle = getClass().getResource("main.css");
		page.getEngine().setUserStyleSheetLocation(mainStyle.toString());
			
		page.getEngine()
			.getLoadWorker()
			.stateProperty()
			.addListener((value, last, current) -> {
				if (current == State.SUCCEEDED) {
					initialize();
				}
			});
		
		try {
			Method main = getClass().getMethod("main");
			if (valid(main)) {
				main.invoke(this);
			}
		} catch (Exception e) { }
		
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
	
	/**
	 * A helper method to add the "click" event handler
	 */
	public void setAction(String selector, EventListener listener) {
		Document document = page.getEngine().getDocument();
		if (document == null) return;
		
		Element element = document.getElementById(selector);
		if (element == null) return;
		
		EventTarget target = (EventTarget)element;
		target.addEventListener("click", listener, false);
	}
	
	/**
	 *  Sets the application's name
	 * 
	 *  Forwards directory to mainStage.setTitle()
	 */
	public void setTitle(String name) {
		mainStage.setTitle(name);
	}
	
	/**
	 * Forwards to page.getEngine().load()
	 * 
	 */
	public void load(String location) {
		page.getEngine().load(location);
	}
	
	/**
	 * Forwards to page.getEngine().loadContent()
	 * 
	 */
	public void loadContent(String content) {
		page.getEngine().loadContent(content);
	}
	
	/**
	 * Forwards to page.getEngine().loadContent()
	 * 
	 */
	public void loadContent(String content, String type) {
		page.getEngine().loadContent(content, type);
	}
	
	/**
	 * Reads file content from resource, and display as the main view.
	 * 
	 */
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
	
	/**
	 * Forwards to getElementById()
	 * @param identifier
	 * @return 
	 */
	public Element getElement(String identifier) {
		return page.getEngine()
				.getDocument()
				.getElementById(identifier);
	}
	
	/**
	 * Forwards to document.createElement()
	 * 
	 */
	public Element createElement(String tag) {
		return page.getEngine()
				.getDocument()
				.createElement(tag);
	}
	
	/**
	 * Forwards to executeScript()
	 * 
	 */
	public void executeScript(String script) {
		page.getEngine().executeScript(script);
	}
	
	/**
	 * Forwards to setTextContent()
	 * 
	 */
	public void setText(String selector, String text) {
		Platform.runLater( () -> {
			var element = getElement(selector);
			if (element == null) return;
			
			try {
				element.setTextContent(text);
			} catch (Exception e) { }
		});
	}

	/**
	 * Helper method to prevent using !=
	 * @param o
	 * @return 
	 */
	boolean valid(Object o) {
		return o != null;
	}
	
	
	/* This is not necessary anymore */
	public static StarryApp getInstance() {
		return instance;
	}

	private static StarryApp instance;
}
