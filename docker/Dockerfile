FROM adoptopenjdk/openjdk11:jre-11.0.2.9-alpine

ADD target/*.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
