
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