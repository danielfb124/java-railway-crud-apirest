FROM eclipse-temurin:21-jdk as build


COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN chmod +x src/app.js

RUN ./mvnw package -DskipTest
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre

ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/app.jar .

RUN useradd runtime 
USER runtime

ENTRYPOINT [ "java","-Dserver.port=${PORT}", "-jar","app.jar" ]