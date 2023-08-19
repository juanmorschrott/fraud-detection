FROM maven as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/fraud-detection.jar

WORKDIR /app
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "fraud-detection.jar"]