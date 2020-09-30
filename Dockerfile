FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/example-service-*.jar  app.jar
RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]

   