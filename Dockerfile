FROM java:8-jdk-alpine

ADD target/microservice.jar /opt/standalone/microservice/bin
WORKDIR /opt/standalone/microservice/bin

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=production microservice.jar