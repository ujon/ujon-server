FROM gradle:jdk17

WORKDIR /ujon-api
COPY ujon-api/build/libs/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/ujon-api/app.jar"]