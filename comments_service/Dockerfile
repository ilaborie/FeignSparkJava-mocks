FROM anapsix/alpine-java:jre8
MAINTAINER Igor Laborie <ilaborie@gmail.com> / Laurent Baresse <laurent.baresse@gmail.com>

COPY target/comments-service-1.0-jar-with-dependencies.jar ./comments-service-1.0.jar

EXPOSE 8091

CMD java -jar comments-service-1.0.jar