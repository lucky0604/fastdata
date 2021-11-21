# Fastdata Annotation tool



## Table of Contents

1. [About the Project](#about-the-project)
1. [Project Status](#project-status)
1. [Getting Started](#getting-started)
    1. [Getting the Source](#getting-the-source)
1. [Authors](#authors)

## About the Project

* Microservices of one-stop annotation tool
* Multiple programming languages (Python, Scala, Java)
* Spark / Flink real-time computation injection


**[Back to top](#table-of-contents)**

## Project Status

Initialize project

**[Back to top](#table-of-contents)**

## Getting Started

Just clone the source

### Getting the Source

```
git clone https://github.com/lucky0604/fastdata.git
```

### build a development environment by manual
1. `fastdata-common` folder
```shell
cd fastdata-common
mvn install
```
2. `fastdata-auth` folder
```shell
cd fastdata-auth/fastdata-auth-client 
mvn install
```
3. `docker-compose` folder
```shell
# first , create an network
docker network create fastdata-net
docker-compose -f docker-compose.yml up -d mysql
docker-compose -f docker-compose.yml up -d redis
docker-compose -f docker-compose.yml up -d rabbitmq
```
4. `fastdata-center` folder
```shell
cd fastdata-center/fastdata-bus
mvn package && mvn docker:build
```
5. `docker-compose` folder
```shell
cd docker-compose
docker-compose -f docker-compose.yml -f docker-compose.nacos.yml up -d nacos
```
6. `fastdata-gateway` folder
```shell
cd fastdata-gateway/fastdata-gateway-web
mvn package && mvn docker:build
cd fastdata-gateway/fastdata-gateway-admin
mvn package && mvn docker:build
```
7. `docker-compose` folder  
init applications' database
```shell
docker-compose up mysql-init
```
8. `docker-compose` folder
```shell
docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d fastdata-gateway-web
docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d fastdata-gateway-admin
```
## issues
### build bus server
- Exception caught: java.util.concurrent.ExecutionException: com.spotify.docker.client.shaded.javax.ws.rs.ProcessingException: com.spotify.docker.client.shaded.org.apache.http.conn.HttpHostConnectException: Connect to localhost:2375 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect -> [Help 1]
  
  > update the docker-maven-plugin to the latest version
## License

Copyright (c) 2021 Lucky

**[Back to top](#table-of-contents)**

## Authors

* **[Lucky](https://github.com/lucky0604)** 
