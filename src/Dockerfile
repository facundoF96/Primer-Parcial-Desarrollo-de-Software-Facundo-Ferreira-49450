FROM alpine:latest as build

RUN apk update \
    && apk add openjdk17 \
    && apk add bash

WORKDIR /app

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build ./build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
