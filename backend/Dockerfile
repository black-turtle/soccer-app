
#
# Build stage
#
FROM maven:3.8.4-openjdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Deploy stage
#
FROM openjdk:8-jdk-slim
RUN mkdir ~/workdir
COPY --from=build /home/app/target/soccer-1.0.0-SNAPSHOT.jar ~/workdir/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","~/workdir/app.jar"]

# FROM maven:3.8.4-openjdk-8-slim
# WORKDIR /workdir
# COPY src /workdir
# COPY pom.xml /workdir
# RUN mvn clean install
# CMD mvn spring-boot:run