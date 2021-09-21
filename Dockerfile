# syntax=docker/dockerfile:1
FROM openjdk:16-alpine3.13
RUN apk update
RUN apk upgrade
RUN apk add bash
RUN apk add curl

ARG PORT=9001
ARG START_ARG=-Dserver.port=${PORT}

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", ${START_ARG}]
