mkdir platform
cd platform

curl --location --output jdk.zip ^
https://download.java.net/java/GA^
/jdk24/1f9ff9062db4449d8ca828c504ffae90/36/GPL^
/openjdk-24_windows-x64_bin.zip

tar xf jdk.zip
move jdk-24 jdk
jdk\bin\java --version


curl --location --output javafx.zip ^
https://download.java.net/java/GA^
/javafx24/bde9f846c551418e80e98679ef280c36/29^
/openjfx-24_windows-x64_bin-sdk.zip

tar xf javafx.zip
move javafx-sdk-24 javafx


mkdir runtime
curl --location --output runtime\json.jar ^
https://repo1.maven.org/maven2/org/json/json/20250107^
/json-20250107.jar

curl --location --output starry.zip ^
https://codestar.work/starry.zip

tar xf starry.zip

curl --output make-windows.bat https://codestar.work/make-windows.bat
make-windows.bat
