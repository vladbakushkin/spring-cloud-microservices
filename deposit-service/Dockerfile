FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY /build/libs/deposit-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "bill-service:8082", "--timeout=90", "--", "java", "-jar", "deposit-service-1.0-SNAPSHOT.jar"]