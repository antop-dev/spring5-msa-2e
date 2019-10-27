FROM openjdk:8
VOLUME /tmp
ADD target/chapter-09.search-1.0.jar search.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/search.jar"]