FROM maven:3.8.3-openjdk-17-slim as BUILDER

WORKDIR /build/

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17.0.1-jdk-slim-bullseye as RUNNER

WORKDIR /app

COPY --from=BUILDER /build/target/budget-manager-api-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "budget-manager-api-0.0.1-SNAPSHOT.jar"]