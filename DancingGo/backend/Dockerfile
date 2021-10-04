FROM azul/zulu-openjdk:8
RUN ./gradlew build
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080