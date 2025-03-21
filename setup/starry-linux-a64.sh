sudo apt install unzip --yes

mkdir platform
cd platform

curl --location --output jdk-linux.tar.gz \
https://download.java.net/java/GA/jdk24\
/1f9ff9062db4449d8ca828c504ffae90/36/GPL\
/openjdk-24_linux-aarch64_bin.tar.gz

tar xf jdk-linux.tar.gz
mv jdk-24 jdk
rm jdk-linux.tar.gz

jdk/bin/java --version


curl --location --output javafx.zip \
https://download2.gluonhq.com/openjfx/24\
/openjfx-24_linux-aarch64_bin-sdk.zip

unzip javafx.zip
mv javafx-sdk-24 javafx
rm javafx.zip

mkdir runtime
curl --location --output runtime/json.jar \
https://repo1.maven.org/maven2/org/json/json/20250107/json-20250107.jar

curl --location --output starry.tar.gz \
https://codestar.work/starry.tar.gz

mkdir code
cd code
tar xf ../starry.tar.gz
cd ..

curl --output make-linux.sh https://codestar.work/make-linux.sh
bash make-linux.sh

