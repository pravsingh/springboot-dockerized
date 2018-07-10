# Introduction
A Sample Application based on Spring Boot which uses Docker plugin to 
build the image of the application.
This document lists out the steps to build, push the image and use it to launch the
server inside the container.


## create account on DockerHub
create account at https://hub.docker.com

## initialize local docker to connect to your account
docker login

## ensure code builds locally
mvn clean install


## create image based on the Dockerfile
mvn install -DskipTests dockerfile:build

### verify the image
docker images

## push the image to DockerHub
mvn install -DskipTests dockerfile:push

### verify if image is published
image pushed at https://hub.docker.com/r/pravsingh/springboot-dockerized/

## remove local image
docker rmi pravsingh/springboot-dockerized

## pull from DockerHub
docker pull pravsingh/springboot-dockerized

## run the app
docker run -p 8080:8080 -t pravsingh/springboot-dockerized

## run the app with specific profile
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t pravsingh/springboot-dockerized

# run the app using spring-boot-maven-plugin
mvn spring-boot:run

# runt the app using jar
./start.sh

## access the application API
http://localhost:8080/health

## Swagger
http://localhost:8080/swagger-ui.html

