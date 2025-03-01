import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		load("https://google.com");
	}
}

/*
javac --class-path "code"          \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	code/Start.java
	
	
java --class-path "code" \
	--module-path "./javafx/lib" \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	Start

*/
