#!/bin/bash

export JAVA_HOME=$HOME/.jdks/corretto-17/
./mvnw clean package -Dmaven.test.skip=true
