FROM anapsix/alpine-java:jre8
MAINTAINER Igor Laborie <ilaborie@gmail.com> / Laurent Baresse <laurent.baresse@gmail.com>

COPY target/authentication-service-1.0-jar-with-dependencies.jar ./authentication-service-1.0.jar

EXPOSE 8093

CMD java -jar authentication-service-1.0.jar