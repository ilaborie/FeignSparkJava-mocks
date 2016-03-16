FROM anapsix/alpine-java:jre8
MAINTAINER Igor Laborie <ilaborie@gmail.com> / Laurent Baresse <laurent.baresse@gmail.com>

COPY target/stocks-service-1.0-jar-with-dependencies.jar ./stocks-service-1.0.jar

EXPOSE 8092

CMD java -jar stocks-service-1.0.jar