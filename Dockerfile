FROM openjdk:21-slim as builder

WORKDIR /blog

COPY . ./

RUN ./mvnw -Dmaven.test.skip -DskipTests clean package

FROM openjdk:21-slim

WORKDIR /blog

RUN mkdir /files

COPY --from=builder /blog/target/blog-1.0-SNAPSHOT.jar /blog/blog.jar

ENTRYPOINT ["java", "-jar", "/blog/blog.jar"]