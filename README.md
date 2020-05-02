# Kafka with reactive streams

## Description 

This is a multi-module project that uses reactive way in services communication 
on the example of currency market. The client receives quotations as soon as they change. 
The project consist of:
 - Kafka message broker (run in *docker);
 - Kafka producer service;
 - Kafka consumer **services;
 - Client service.
 
The consumer services can generate a reactive stream (using Flux). They're acting as a service bridge
between kafka and client. 
 
*Containers configuration is in docker/docker-compose.yml 
 
**There're two consumer services. it's just for demonstrating group usage. The client service 
applies to 'consumer-one' service.

### Run
First of all kafka and zookeper must be *started.

```
docker-compose up
```