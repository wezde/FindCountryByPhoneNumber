FROM gradle:7.4.2-jdk11-jammy AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM eclipse-temurin:11-jammy
WORKDIR /app
COPY --from=build /app/build/libs/FindCountryByPhoneNumber-0.0.1-SNAPSHOT.jar /app/app.jar
COPY src/main/resources/static /app/static
COPY .env /app/.env
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.jar"]