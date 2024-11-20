FROM openjdk:17-jdk-slim AS MAVEN_BUILD
COPY ./ ./
RUN ./mvnw clean install -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=MAVEN_BUILD /target/TaskManagementSystem-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]