#!/bin/bash

export JAVA_HOME=$HOME/.jdks/corretto-15.0.2
./mvnw clean package -Dmaven.test.skip=true
