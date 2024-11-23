FROM gradle:jdk21
LABEL org.opencontainers.image.source=https://github.com/OWNER/REPO

ENV SPRING_PROFILES_ACTIVE=prod

WORKDIR /app

COPY ujon-api/build/libs/*.jar ./ujon-api.jar

EXPOSE 8080

ENTRYPOINT [
    "java","-jar", "/app/ujon-api.jar",
    "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"
]