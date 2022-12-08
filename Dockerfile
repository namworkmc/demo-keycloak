FROM eclipse-temurin:17.0.5_8-jre-alpine
EXPOSE 8080
COPY target/demo-keycloak.jar demo-keycloak.jar
ENTRYPOINT ["java","-jar","/demo-keycloak.jar"]