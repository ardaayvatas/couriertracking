FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--strict", "--timeout=30", "--", "java", "-jar", "app.jar"]
