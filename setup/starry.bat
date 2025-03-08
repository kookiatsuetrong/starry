mkdir platform
cd platform


curl --location --output jdk.zip ^
https://download.java.net/java/GA^
/jdk23.0.2/6da2a6609d6e406f85c491fcb119101b/7/GPL^
/openjdk-23.0.2_windows-x64_bin.zip

tar xf jdk.zip
move jdk-23.0.2 jdk
jdk\bin\java --version


curl --location --output javafx.zip ^
https://download.java.net/java/GA^
/javafx23.0.2/512f2f157741485abda37a0a95f69984/3^
/openjfx-23.0.2_windows-x64_bin-sdk.zip

tar xf javafx.zip
move javafx-sdk-23.0.2 javafx


mkdir runtime
curl --location --output runtime\json.jar ^
https://repo1.maven.org/maven2/org/json/json/20250107^
/json-20250107.jar


curl --location --output starry.zip ^
https://codestar.work/starry.zip

tar xf starry.zip

curl --output make-windows.bat https://codestar.work/make-windows.bat
make-windows.bat
