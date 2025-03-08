mkdir platform
cd platform

curl --location --output jdk-linux.tar.gz \
https://download.java.net/java/GA/jdk23.0.2\
/6da2a6609d6e406f85c491fcb119101b/7/GPL\
/openjdk-23.0.2_linux-x64_bin.tar.gz

tar xf jdk-linux.tar.gz
mv jdk-23.0.2 jdk
rm jdk-linux.tar.gz

jdk/bin/java --version

curl --location --output javafx-linux.tar.gz \
https://download.java.net/java/GA/javafx23.0.2\
/512f2f157741485abda37a0a95f69984/3\
/openjfx-23.0.2_linux-x64_bin-sdk.tar.gz

tar xf javafx-linux.tar.gz
mv javafx-sdk-23.0.2 javafx
rm javafx-linux.tar.gz

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
