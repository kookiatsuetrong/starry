mkdir platform
cd platform

curl --location --output jdk-darwin.tar.gz \
https://download.java.net/java/GA/jdk24\
/1f9ff9062db4449d8ca828c504ffae90/36/GPL\
/openjdk-24_macos-aarch64_bin.tar.gz

tar xf jdk-darwin.tar.gz
mv jdk-24.jdk/Contents/Home jdk
rm jdk-darwin.tar.gz
rm -r jdk-24.jdk

jdk/bin/java --version

curl --location --output javafx-darwin.tar.gz \
https://download.java.net/java/GA/javafx24\
/bde9f846c551418e80e98679ef280c36/29\
/openjfx-24_macos-aarch64_bin-sdk.tar.gz

tar xf javafx-darwin.tar.gz
mv javafx-sdk-24 javafx
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

