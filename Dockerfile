FROM eclipse-temurin:25-jdk-noble AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN chmod +x mvnw && ./mvnw -B clean package -DskipTests

FROM eclipse-temurin:25-jre-noble
COPY --from=builder /usr/src/app/target/fraud-detection-*.jar /app/fraud-detection.jar

WORKDIR /app
USER 10001:10001
CMD ["java", "--enable-native-access=ALL-UNNAMED", "-Dspring.profiles.active=docker", "-jar", "/app/fraud-detection.jar"]