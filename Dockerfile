FROM openjdk:17-jdk-alpine

COPY target/ges-event.jar ges-event.jar

ENTRYPOINT ["java","-jar","/ges-event.jar"]