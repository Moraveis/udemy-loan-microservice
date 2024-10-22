FROM openjdk:17-slim

MAINTAINER joao-vitor-moraveis

COPY target/loans-0.0.1-SNAPSHOT.jar loans.jar

ENTRYPOINT ["java", "-jar", "loans.jar"]
