FROM anapsix/alpine-java:jre8
MAINTAINER Igor Laborie <ilaborie@gmail.com> / Laurent Baresse <laurent.baresse@gmail.com>

COPY target/reference-service-1.0-jar-with-dependencies.jar ./reference-service-1.0.jar

EXPOSE 8090

CMD java -jar reference-service-1.0.jar