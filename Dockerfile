FROM openjdk:8-jdk-alpine

EXPOSE 8080
USER root
ENV TZ='America/Sao_Paulo'
ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar
# Usuario default do container
USER jboss

# run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","mongodb-spring-data-0.0.1-SNAPSHOT.jar"]