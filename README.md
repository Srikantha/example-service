<h1 align="center"> Example microservice </h1> <br>

<p align="center">
  Example microservice description.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Testing](#testing)
- [API](#requirements)
- [Acknowledgements](#acknowledgements)




## Introduction

In short, the microservice architectural style is an approach to developing a single application as a suite of small services, 
each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API or event-driven.

This sample service is adopting the microservice architecture style.

## Features
Description of features

* Include a list of employees
* Get a particular employee based on the id
* Save an employee
* Delete an employee based on the id


## Requirements
The application can be run locally or in a docker container, the requirements for each setup are listed below.

### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)


### Docker
* [Docker](https://www.docker.com/get-docker)

### Kafka
* [Kafka](https://kafka.apache.org/)

### H2 inmemory database
* [H2 DB](https://www.h2database.com/html/main.html)

## Quick Start
Once you have the required environment setup, you can run the server in a docker container or on your local machine.

### Run Local
```bash
$ mvn spring-boot:run
```

Application will run by default on port `8080`

Configure the port by changing `server.port` in __application.yml__


### Run Docker

First build the image:
```bash
$ docker build -t example-service-spring-boot-docker .
```

When ready, run it:
```bash
$ docker run -p 8080:8080 example-service-spring-boot-docker
```

Application will run by default on port `8080`


## Testing
Integration tests will execute while building the application.


## API
API Reference with examples, or a link to a wiki or other documentation source.
In local, the endpoints can be access via below links.

* Get all employees: GET (http://localhost:8080/example-service/employees) 
* Get simple employee: GET (http://localhost:8080/example-service/employees/1)
* Create new employee: POST (http://localhost:8080/example-service/employees)
* Delete an employee: DELETE (http://localhost:8080/example-service/employees/1)