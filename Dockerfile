FROM openjdk:17
COPY target/courier-tracking-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]