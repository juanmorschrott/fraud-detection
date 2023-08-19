# Fraud Detection

This very simple and naive application has been created with the intention of show a possible
hexagonal architecture implementation using Spring Boot.
The main goal is to structure your application layers in receiving user interactions 
(web endpoint, cli and files) as input and stores data (database) as output.

![hexagonal architecture](hexagonal-architecture.png)

### Context

We are worried about fraud in electricity readings, and we have decided to implement a suspicious reading detector.
Some clients have phoned us suspecting some squatters have been tapping into their electricity lines and this is why you 
may find some extremely high readings compared to their regular usage. At the same time, we suspect some clients are 
tapping their building electricity lines and you may also find extremely low readings.

As we all know, many systems in Spain are a bit old-fashioned and get some readings in XML and some others in CSV, 
so we need to be able to implement adaptors for both inputs.

For this first iteration, we will try to identify readings that are 50% higher than the annual mean.

## Layers

Hexagonal Architecture was designed to make your software "pluggable" following the principle of ports and adapters. 
You should be able to make your pieces interact knowing nothing about each other. 
The user interaction could be a Rest endpoint and your data source could be a database or a file, it doesn't matter.

### Application

This layer is the core of our application. It contains the definitions of our ports and the main functionality.

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hellolight
│   │   │           └── frauddetection
...
│   │   │                   ├── application
│   │   │                   │   ├── exception
│   │   │                   │   │   └── InvalidDataFileTypeException.java
│   │   │                   │   ├── port
│   │   │                   │   │   ├── in
│   │   │                   │   │   │   ├── DetectFraudUseCase.java
│   │   │                   │   │   │   └── ReadingsPort.java
│   │   │                   │   │   └── out
│   │   │                   │   │       └── StoreResultPort.java
│   │   │                   │   └── service
│   │   │                   │       └── DetectFraudService.java
```

### Domain

Domain layer contains the common language that business people and developers should use.

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hellolight
│   │   │           └── frauddetection
...
│   │   │                   └── domain
│   │   │                       ├── Reading.java
│   │   │                       └── Result.java
```

### Adapter

Here is where you add your adapters. Adapters can be input or output e.g. A REST endpont or a Database.

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hellolight
│   │   │           └── frauddetection
...
│   │   │                   ├── adapter
│   │   │                   │   ├── in
│   │   │                   │   │   ├── cli
│   │   │                   │   │   │   └── FraudDetectionShell.java
│   │   │                   │   │   ├── file
│   │   │                   │   │   │   ├── CsvReader.java
│   │   │                   │   │   │   ├── CsvReading.java
│   │   │                   │   │   │   ├── CsvReadingToReadingConverter.java
│   │   │                   │   │   │   ├── ReadingsAdapter.java
│   │   │                   │   │   │   ├── XmlReader.java
│   │   │                   │   │   │   ├── XmlReading.java
│   │   │                   │   │   │   ├── XmlReadings.java
│   │   │                   │   │   │   └── XmlReadingsToReadingsConverter.java
│   │   │                   │   │   └── web
│   │   │                   │   │       └── FraudDetectionController.java
│   │   │                   │   └── out
│   │   │                   │       └── persistence
│   │   │                   │           ├── ResultAdapter.java
│   │   │                   │           ├── ResultEntity.java
│   │   │                   │           ├── ResultRepository.java
│   │   │                   │           └── ResultToResultEntityConverter.java
```

## Running the application

### Dependencies

- Java JDK 17
- Maven > 3.6.*
- Docker & Docker-Compose

### Using Docker & Docker-Compose

Execute:

```bash
$ cd ./fraud-detection
$ docker-compose build
$ docker-compose run --rm fraud-detection
```

### Using local JVM & Maven

Execute:

```bash
$ cd ./fraud-detection
$ mvn spring-boot:run
```

The interaction with our application will be a shell. Once started you can run:

```bash
shell:>scan --fileName 2016-readings.xml
```

It will parse a list of light consumption readings list, searching for a 50% higher of the average, in order to
detect a possible fraud and show a results table at the end:

```bash

| Client              | Month              | Suspicious         | Mean   |
 ---------------------------------------------------------------------------
| 583ef6329d89b       | SEPTEMBER          | 162078             | 63849,75 |
```

You will find more information about how to use the application by running:

```bash
shell:>info
```
