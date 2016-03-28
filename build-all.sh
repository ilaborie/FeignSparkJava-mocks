#!/usr/bin/env bash

rm -rf microservices
mkdir microservices

pushd authentication_service
mvn clean install -DskipTest
cp target/authentication-service-1.0-jar-with-dependencies.jar ../microservices/authentication-service-1.0.jar
echo "java -jar authentication-service-1.0.jar" > ../microservices/start-authentication-service.bat
echo "java -jar authentication-service-1.0.jar" > ../microservices/start-authentication-service.sh
chmod a+x ../microservices/start-authentication-service.sh
popd

pushd comments_service
mvn clean install -DskipTest
cp target/comments-service-1.0-jar-with-dependencies.jar ../microservices/comments-service-1.0.jar
echo "java -jar comments-service-1.0.jar" > ../microservices/start-comments-service.bat
echo "java -jar comments-service-1.0.jar" > ../microservices/start-comments-service.sh
chmod a+x ../microservices/start-comments-service.sh
popd

pushd reference_service
mvn clean install -DskipTest
cp target/reference-service-1.0-jar-with-dependencies.jar ../microservices/reference-service-1.0.jar
echo "java -jar reference-service-1.0.jar" > ../microservices/start-reference-service.bat
echo "java -jar reference-service-1.0.jar" > ../microservices/start-reference-service.sh
chmod a+x ../microservices/start-reference-service.sh
popd

pushd stocks_service
mvn clean install -DskipTest
cp target/stocks-service-1.0-jar-with-dependencies.jar ../microservices/stocks-service-1.0.jar
echo "java -jar stocks-service-1.0.jar" > ../microservices/start-stocks-service.bat
echo "java -jar stocks-service-1.0.jar" > ../microservices/start-stocks-service.sh
chmod a+x ../microservices/start-stocks-service.sh
popd