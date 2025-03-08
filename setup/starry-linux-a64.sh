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

curl --location --output starry.tar.gz \
https://codestar.work/starry.tar.gz

mkdir code
cd code
tar xf ../starry.tar.gz
cd ..

curl --output make-linux.sh https://codestar.work/make-linux.sh
bash make-linux.sh
