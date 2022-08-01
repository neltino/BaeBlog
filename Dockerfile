FROM openjdk:17.0.2
RUN mkdir /app
ADD target/fashion-blog.jar /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/fashion-blog.jar"]
