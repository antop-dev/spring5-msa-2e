FROM openjdk:8
VOLUME /tmp
ADD target/chapter-09.website-1.0.jar website.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/website.jar"]