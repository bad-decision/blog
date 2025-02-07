FROM openjdk:21-slim as builder

WORKDIR /blog

COPY . .

RUN ./gradlew clean war

FROM tomcat:10.1.24-jdk21

ENV TZ="Europe/Moscow"

RUN apt-get update && apt-get install -y fontconfig libfreetype6

COPY --from=builder /blog/build/libs/blog.war /usr/local/tomcat/webapps/ROOT.war