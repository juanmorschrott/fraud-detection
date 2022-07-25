FROM maven as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/fraud-detection.jar
WORKDIR /app
CMD ["java", "-jar", "fraud-detection.jar", "-Dspring.profiles.active=docker"]