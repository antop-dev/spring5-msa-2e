FROM openjdk:8
VOLUME /tmp
ADD target/chapter-09.book-1.0.jar book.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "/book.jar"]