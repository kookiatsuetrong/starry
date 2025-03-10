mkdir platform
cd platform

curl --location --output jdk-darwin.tar.gz \
https://download.java.net/java/GA/jdk23.0.2\
/6da2a6609d6e406f85c491fcb119101b/7/GPL\
/openjdk-23.0.2_macos-x64_bin.tar.gz


tar xf jdk-darwin.tar.gz
mv jdk-23.0.2.jdk/Contents/Home jdk
rm jdk-darwin.tar.gz
rm -r jdk-23.0.2.jdk

jdk/bin/java --version

curl --location --output javafx-darwin.tar.gz \
https://download.java.net/java/GA/javafx23.0.2\
/512f2f157741485abda37a0a95f69984/3\
/openjfx-23.0.2_macos-x64_bin-sdk.tar.gz

tar xf javafx-darwin.tar.gz
mv javafx-sdk-23.0.2 javafx
rm javafx-darwin.tar.gz

mkdir runtime
curl --location --output runtime/json.jar \
https://repo1.maven.org/maven2/org/json/json/20250107/json-20250107.jar

curl --location --output starry.tar.gz \
https://codestar.work/starry.tar.gz

mkdir code
cd code
tar xf ../starry.tar.gz
cd ..

curl --output make-darwin.sh https://codestar.work/make-darwin.sh
bash make-darwin.sh
