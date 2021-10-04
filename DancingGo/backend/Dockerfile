FROM azul/zulu-openjdk:8 AS builder
WORKDIR /backend
COPY . .
RUN chmod +x ./gradlew

RUN ./gradlew clean build

FROM azul/zulu-openjdk:8
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080