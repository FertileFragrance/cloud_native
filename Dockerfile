# Docker image for springboot application
# VERSION 0.0.1

FROM openjdk:8-jre-alpine

ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

WORKDIR /app

ADD target/demo-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","/app.jar"]