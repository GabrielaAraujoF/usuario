FROM gradle:8-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle bootJar


FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar usuario.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","usuario.jar"]