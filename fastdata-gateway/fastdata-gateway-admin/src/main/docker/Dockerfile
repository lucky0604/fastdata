FROM java:alpine
VOLUME /tmp
ADD fastdata-gateway-admin-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]