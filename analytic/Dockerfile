FROM openjdk:21-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/analytic-0.0.1-SNAPSHOT.jar"]