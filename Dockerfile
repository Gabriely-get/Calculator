FROM gradle:7-jdk11

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build
COPY /build/libs/Calculator-1.0-SNAPSHOT.jar /home/Calculator/Calculator-1.0-SNAPSHOT.jar
WORKDIR /home/Calculator/
EXPOSE 8888
CMD ["java", "-jar", "Calculator-1.0-SNAPSHOT.jar"]