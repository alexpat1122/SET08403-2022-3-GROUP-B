FROM openjdk:latest
COPY target/DevOps-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOps-1.0-SNAPSHOT-jar-with-dependencies.jar"]
