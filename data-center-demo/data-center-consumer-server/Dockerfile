FROM java:8-jre

MAINTAINER jmanagerboot@gmail.com

RUN mkdir -p /data-receiving/data-center-server

WORKDIR /data-receiving/data-center-server

ADD ./data-center-demo/data-center-consumer-server/target/data-center-server.jar app.jar

ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar --csp.sentinel.app.type=0"]

EXPOSE 8769









