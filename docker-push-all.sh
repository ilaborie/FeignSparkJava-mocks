#!/usr/bin/env bash

pushd authentication_service
mvn clean install docker:build docker:push -DskipTests
popd

pushd comments_service
mvn clean install docker:build docker:push -DskipTests
popd

pushd reference_service
mvn clean install docker:build docker:push -DskipTests
popd

pushd stocks_service
mvn clean install docker:build docker:push -DskipTests
popd