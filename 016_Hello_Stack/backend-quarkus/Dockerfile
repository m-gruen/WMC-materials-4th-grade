# Build with Maven (uses the Maven wrapper in the repo)
FROM maven:3.9.12-eclipse-temurin-25 AS build
WORKDIR /work
COPY . .
RUN mvn -q -DskipTests package

# Run on UBI OpenJDK 25 runtime
FROM registry.access.redhat.com/ubi9/openjdk-25-runtime:latest
WORKDIR /app
COPY --from=build /work/target/quarkus-app/ /app/
EXPOSE 8080
CMD ["java","-jar","/app/quarkus-run.jar"]
