#!/usr/bin/env bash

apt-get update

# Install Java 17
wget https://download.java.net/java/GA/jdk17/0d483333a00540d886896bac774ff48b/35/GPL/openjdk-17_linux-x64_bin.tar.gz
tar xvf openjdk-17_linux-x64_bin.tar.gz
mv -f jdk-17 /opt/

# Clean up
rm openjdk-17*

JAVA_HOME=/opt/jdk-17

echo "export JAVA_HOME=$JAVA_HOME" > /home/vagrant/.profile
echo "export PATH=$PATH:$JAVA_HOME/bin" >> /home/vagrant/.profile
source ~/.profile