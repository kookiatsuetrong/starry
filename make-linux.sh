jdk/bin/javac --class-path "code"  \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	--add-modules javafx.swing     \
	code/Start.java

jdk/bin/java --class-path "code"   \
	--module-path "./javafx/lib"   \
	--add-modules javafx.controls  \
	--add-modules javafx.web       \
	--add-modules javafx.swing     \
	--enable-native-access javafx.graphics \
	--enable-native-access javafx.web      \
	--sun-misc-unsafe-memory-access=allow  \
	Start
