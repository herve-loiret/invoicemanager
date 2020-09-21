FROM maven:3.6.2-jdk-8 AS build

WORKDIR /srv
COPY src src
COPY pom.xml .

RUN mvn clean package

FROM maven:3-jdk-8

WORKDIR /srv

COPY --from=build /srv/target/invoicemanager-*.jar .

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=embeddedS3 invoicemanager-0.0.1-SNAPSHOT.jar
