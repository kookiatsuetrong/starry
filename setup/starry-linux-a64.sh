sudo apt install unzip --yes

mkdir platform
cd platform

curl --location --output jdk-linux.tar.gz \
https://corretto.aws/downloads/latest\
/amazon-corretto-23-aarch64-linux-jdk.tar.gz

tar xf jdk-linux.tar.gz
mv amazon-corretto-23.0.2.7.1-linux-aarch64 jdk
rm jdk-linux.tar.gz

jdk/bin/java --version

curl --location --output javafx-linux.zip \
https://download2.gluonhq.com/openjfx/23.0.2\
/openjfx-23.0.2_linux-aarch64_bin-sdk.zip

unzip javafx-linux.zip
mv javafx-sdk-23.0.2 javafx
rm javafx-linux.zip

mkdir runtime
curl --location --output runtime/json.jar \
https://repo1.maven.org/maven2/org/json/json/20250107/json-20250107.jar

mkdir code
curl --location --output code/Start.java \
https://raw.githubusercontent.com\
/kookiatsuetrong/starry/refs/heads/main/code/Start.java

curl --location --output code/main.html \
https://raw.githubusercontent.com\
/kookiatsuetrong/starry/refs/heads/main/code/main.html

mkdir code/starry

curl --location --output code/starry/StarryApp.java \
https://raw.githubusercontent.com\
/kookiatsuetrong/starry/refs/heads/main/code/starry/StarryApp.java

jdk/bin/javac --class-path "code" \
--module-path "javafx/lib"        \
--add-modules javafx.controls     \
--add-modules javafx.web          \
code/Start.java

jdk/bin/java --class-path "code"  \
--module-path "javafx/lib"        \
--add-modules javafx.controls     \
--add-modules javafx.web          \
Start

