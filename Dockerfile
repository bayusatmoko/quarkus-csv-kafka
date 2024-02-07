FROM maven:3-openjdk-17-slim as BUILDER
WORKDIR /build

COPY target/quarkus-app target/quarkus-app

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app/

COPY --from=BUILDER /build/target/quarkus-app /app/quarkus-app
CMD java -jar /app/quarkus-app/quarkus-run.jar