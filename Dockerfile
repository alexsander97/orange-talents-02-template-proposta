FROM openjdk:11
MAINTAINER Alexsander Camilo
ARG JAR_FILE=target/*.jar
ENV DB_URL=jdbc:h2:mem:test
ENV DB_DRIVER=org.h2.Driver
ENV DB_USERNAME=sa
ENV DB_PASSWORD=
ENV JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV JPA_SHOW_SQL=true
ENV JPA_DDL_AUTO=update
ENV API_FINANCIAL_ANALYSIS=http://analise:9999
ENV API_CARD=http://contas:8888
ENV KEYCLOAK_ISSUER_URI=http://keycloak:8080/auth/realms/proposta
ENV KEYCLOAK_JWKS_URI=http://keycloak:8080/auth/realms/proposta/protocol/openid-connect/certs
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]


