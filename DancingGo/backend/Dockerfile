FROM azul/zulu-openjdk:8 AS builder
WORKDIR /backend
COPY . .
RUN chmod +x ./gradlew

RUN ./gradlew clean build
RUN ls /backend/build/libs

FROM azul/zulu-openjdk:8
COPY --from=builder /backend/build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]