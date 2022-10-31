FROM openjdk:11
ADD target/*.jar bea-blog.jar
ENTRYPOINT ["java", "-jar", "/bea-blog.jar"]