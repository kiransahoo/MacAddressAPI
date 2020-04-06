# For Java 8, try this
FROM openjdk:8-jdk-alpine

# For Java 11, try this and update the pom.xml
#FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/macaddressapi-web.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/macaddressapi-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]

## sudo docker run -p 443:8443 -t macaddressapi:1.0
