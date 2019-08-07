#
# Build stage
#
FROM maven:3.6-jdk-8-slim AS build
COPY src /build/src
COPY pom.xml /build/
RUN mvn -f /build/pom.xml clean package -D skipTests

# ENV variables


ENV RDS_PASSWORD password
ENV RDS_USERNAME postgres
ENV RDS_HOSTNAME localhost
ENV RDS_PORT 5432
ENV RDS_DB_NAME jrvstrading
ENV IEX_TOKEN sk_29781e986db2408aabbdae37117c69b5
ENV RDS_URL jdbc:postgresql://${RDS_HOSTNAME}:${RDS_PORT}/${RDS_DB_NAME}

#
# Package stage
#
FROM openjdk:8-alpine
COPY --from=build /build/target/trading-0.0.1-SNAPSHOT.jar /usr/local/app/trading/lib/trading_app.jar
ENTRYPOINT ["java","-jar","/usr/local/app/trading/lib/trading_app.jar"]
