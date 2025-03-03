import starry.StarryApp;
import javafx.application.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.html.HTMLInputElement;

public class Start extends StarryApp {
	
	public void main() {
		loadContent(content);
	}
	
	public void setup() {
		setAction("add-button", e -> add() );
	}
	
	void add() {
		Document document = page.getEngine().getDocument();
		HTMLInputElement input = (HTMLInputElement)document
								.getElementById("task");
		Element report = document.getElementById("report");
		Element item = document.createElement("p");
		try {
			item.setTextContent(input.getValue());
			report.appendChild(item);
		} catch (Exception e) { }
		input.setValue("");
	}
	
	String content = 
	"""
	<body>
		<input id="task" />
		<button id="add-button">Add</button>
		<section id="report"></section>
	</body>
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
	}
	button:hover {
	 	background: steelblue;
	}
	input {
		border: none;
		border-radius: .35rem;
		height: 2rem;
		padding: .25rem .5rem;
		background: #eee;
	}
	</style>
	""";
}

/*
javac --class-path "code"      \
--module-path "./javafx/lib"   \
--add-modules javafx.controls  \
--add-modules javafx.web       \
code/Start.java
	
	
java --class-path "code"       \
--module-path "./javafx/lib"   \
--add-modules javafx.controls  \
--add-modules javafx.web       \
Start

javac --class-path "code"      ^
--module-path "javafx\lib"     ^
--add-modules javafx.controls  ^
--add-modules javafx.web       ^
code\Start.java

java --class-path "code"       ^
--module-path "javafx\lib"     ^
--add-modules javafx.controls  ^
--add-modules javafx.web       ^
Start

*/

/*

public interface Node {

	public String getNodeName();
	public String getNodeValue()
							  throws DOMException;
	public void setNodeValue(String nodeValue)
							  throws DOMException;
	public short getNodeType();
	public Node getParentNode();
	public NodeList getChildNodes();
	public Node getFirstChild();
	public Node getLastChild();
	public Node getPreviousSibling();
	public Node getNextSibling();
	public NamedNodeMap getAttributes();
	public Document getOwnerDocument();
	public Node insertBefore(Node newChild,
							Node refChild)
							throws DOMException;
	public Node replaceChild(Node newChild,
							Node oldChild)
							throws DOMException;
	public Node removeChild(Node oldChild)
							throws DOMException;
	public Node appendChild(Node newChild)
							throws DOMException;
	public boolean hasChildNodes();
	public Node cloneNode(boolean deep);
	public void normalize();
	public boolean isSupported(String feature,
							   String version);
	public String getNamespaceURI();
	public String getPrefix();
	public void setPrefix(String prefix)
							throws DOMException;
	public String getLocalName();
	public boolean hasAttributes();
	public String getBaseURI();
	public short compareDocumentPosition(Node other)
							throws DOMException;
	public String getTextContent()
							throws DOMException;
	public void setTextContent(String textContent)
							throws DOMException;
	public boolean isSameNode(Node other);
	public String lookupPrefix(String namespaceURI);
	public boolean isDefaultNamespace(String namespaceURI);
	public String lookupNamespaceURI(String prefix);
	public boolean isEqualNode(Node arg);
	public Object getFeature(
					String feature,
					String version);
	public Object setUserData(
					String key,
					Object data,
					UserDataHandler handler);
	public Object getUserData(String key);
}

public interface Element extends Node {

	public String getTagName();
	public String getAttribute(String name);
	public void setAttribute(
					String name,
					String value) throws DOMException;
	public void removeAttribute(String name) throws DOMException;
	public Attr getAttributeNode(String name);
	public Attr setAttributeNode(Attr newAttr) throws DOMException;
	public Attr removeAttributeNode(Attr oldAttr) throws DOMException;

	public NodeList getElementsByTagName(String name);
	public String getAttributeNS(
						String namespaceURI,
						String localName) throws DOMException;
	public void setAttributeNS(
						String namespaceURI,
						String qualifiedName,
						String value) throws DOMException;
	public void removeAttributeNS(
						String namespaceURI,
						String localName) throws DOMException;
	public Attr getAttributeNodeNS(
						String namespaceURI,
						String localName) throws DOMException;

	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException;

	public NodeList getElementsByTagNameNS(
						String namespaceURI,
						String localName) throws DOMException;
	public boolean hasAttribute(String name);
	public boolean hasAttributeNS(
						String namespaceURI,
						String localName) throws DOMException;

	public TypeInfo getSchemaTypeInfo();

	public void setIdAttribute(
						String name,
						boolean isId) throws DOMException;

	public void setIdAttributeNS(
						String namespaceURI,
						String localName,
						boolean isId) throws DOMException;

	public void setIdAttributeNode(
						Attr idAttr,
						boolean isId) throws DOMException;
}

public interface Event {
	public String getType();
	public EventTarget getTarget();
	public EventTarget getCurrentTarget();
	public short getEventPhase();
	public boolean getBubbles();
	public boolean getCancelable();
	public long getTimeStamp();
	public void stopPropagation();
	public void preventDefault();

	public void initEvent(String eventTypeArg,
						boolean canBubbleArg,
						boolean cancelableArg);
}

public interface EventTarget {

	public void addEventListener(
					String type,
					EventListener listener,
					boolean useCapture);

	public void removeEventListener(
					String type,
					EventListener listener,
					boolean useCapture);

	public boolean 
	dispatchEvent(Event evt) throws EventException;
}

*/
