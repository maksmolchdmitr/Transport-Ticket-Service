FROM openjdk:21-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/core-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080