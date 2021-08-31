#!/bin/bash

export JAVA_HOME=/usr/lib/jvm/jre-16-openjdk
./mvnw clean package -Dmaven.test.skip=true
