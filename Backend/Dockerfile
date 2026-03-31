FROM amazoncorretto:17-alpine

WORKDIR /app

COPY build/libs/Finance-0.0.1-SNAPSHOT.jar /app/Finance.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/Finance.jar"]
