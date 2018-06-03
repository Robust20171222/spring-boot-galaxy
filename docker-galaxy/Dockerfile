FROM java:8
COPY ./target/docker-galaxy.jar /usr/src/hola/
WORKDIR /usr/src/hola
EXPOSE 8085
CMD ["java", "-jar", "docker-galaxy.jar"]