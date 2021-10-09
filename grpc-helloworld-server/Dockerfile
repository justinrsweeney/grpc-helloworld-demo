FROM openjdk:17-alpine

ADD build/libs/*.jar app.jar

ENV JAVA_OPTS="-Xms1024m -Xmx1024m"

EXPOSE 5000

CMD ["java", "-jar", "app.jar", "$JAVA_OPTS"]