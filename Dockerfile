FROM openjdk:17-slim

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

COPY target/loans-0.0.1-SNAPSHOT.jar loans.jar

ENTRYPOINT ["java", "-jar", "loans.jar"]
