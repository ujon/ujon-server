FROM gradle:jdk21
LABEL org.opencontainers.image.source=https://github.com/OWNER/REPO

WORKDIR /ujon-api
COPY ujon-api/build/libs/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/ujon-api/app.jar"]