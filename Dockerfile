FROM openjdk:17
MAINTAINER com.defers.crm
COPY target/orders-0.0.1-SNAPSHOT.jar orders-0.0.1.jar
ENTRYPOINT ["java","-jar","/orders-0.0.1.jar"]