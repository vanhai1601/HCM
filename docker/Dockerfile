FROM 10.60.156.72/library/openjdk:8-jdk-alpine

WORKDIR /

COPY ./serviceEtc-0.0.1-SNAPSHOT.jar ./serviceEtc-0.0.1-SNAPSHOT.jar

RUN cp /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime

EXPOSE 8900

ENTRYPOINT ["java","-jar","/serviceEtc-0.0.1-SNAPSHOT.jar"]
