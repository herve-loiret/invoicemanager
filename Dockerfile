FROM maven:3-jdk-8

WORKDIR /srv

COPY target/invoicemanager-*.jar .

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=embeddedS3 invoicemanager-0.0.1-SNAPSHOT.jar
