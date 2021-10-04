FROM azul/zulu-openjdk:8 AS builder
WORKDIR /backend
COPY . .
RUN chmod +x ./gradlew

RUN ./gradlew clean build
RUN ls /build/libs

FROM azul/zulu-openjdk:8
COPY --from=builder /backend/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "./app.jar"]