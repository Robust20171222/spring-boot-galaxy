FROM addo/centos-java8
EXPOSE 6013
VOLUME /tmp
ADD concurrency-galalxy.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]