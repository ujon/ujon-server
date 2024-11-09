FROM gradle:jdk21
LABEL org.opencontainers.image.source=https://github.com/OWNER/REPO

WORKDIR /app

COPY ujon-api/build/libs/*.jar ./ujon-api.jar

RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/app/ujon-api.jar"]