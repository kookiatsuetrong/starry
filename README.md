## The Starry Framework

The framework for writing modern Java desktop application.


```java
import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		load("https://google.com");
	}

}
```

```bash
javac --class-path "code"          \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	code/Start.java
```


```bash
java --class-path "code" \
	--module-path "./javafx/lib" \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	Start
```