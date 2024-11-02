# Fase de construcción
FROM alpine:3.17 as build

# Instalar JDK y bash
RUN apk update && apk add --no-cache openjdk17 bash

WORKDIR /app

# Copiar todo el código fuente
COPY . .

# Dar permisos de ejecución al script de Gradle y construir la aplicación
RUN chmod +x ./gradlew && ./gradlew bootJar --no-daemon

# Fase de ejecución
FROM openjdk:17-jdk-alpine
EXPOSE 8080

# Copiar el JAR generado desde la fase de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
