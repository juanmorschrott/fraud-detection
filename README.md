# Fraud Detection

This very simple and naive application has been created with the intention of show a possible
hexagonal architecture implementation using Spring Boot.
The main goal is to structure your application layers in a way that it receives a user interaction (input) and 
retrieves or stores data from a source (output).

![hexagonal architecture](hexagonal-architecture.png)

## Layers

Hexagonal Architecture was designed to make your software "pluggable". You should be able to make your pieces interact knowing nothing about each other. The user interaction could be a Rest endpoint and your data source could be a database or a file, it doesn't matter.

### Application

This layer contains the user interaction parts. Here you will find Rest Endpoints, user interfaces, scheduled scripts or CLI components.

```bash
src
  ├── main
     ├── java
         └── com
             └── hellolight
                 └── frauddetection
                     ├── application
                         ├── cli
                         │   └── FraudDetectionShell.java
                         └── rest
                             └── FraudDetectionController.java
```

### Domain

All your business logic remains on the domain. Here you define your model (the common language that developers and business
people should use), and the contract with the "outside world". Those are at the /model and /port folders respectively.

```bash
src
  ├── main
     ├── java
         └── com
             └── hellolight
                 └── frauddetection
...
                     ├── domain
                         ├── exception
                         │   └── FraudDetectionException.java
                         ├── model
                         │   ├── Reading.java
                         │   └── Result.java
                         ├── port
                         │   ├── input
                         │   │   └── FraudDetectionService.java
                         │   └── output
                         │       └── FileReadingsProvider.java
                         └── service
                             └── FraudDetectionServiceImpl.java
```

### Infrastructure

Inside our last layer we have all our output implementation. Here you can find Database, Cache, Files or any kind of data source configuration needed in order to make the application work. In our particular business case we have the Csv and Xml files implementation of ReadingsProvider.

```bash
src
  ├── main
     ├── java
         └── com
             └── hellolight
                 └── frauddetection
...
                     └── infrastructure
                         ├── configuration
                         │   └── BeanConfiguration.java
                         ├── csv
                         │   ├── adapter
                         │   │   └── CsvReadingsAdapter.java
                         │   ├── converter
                         │   │   └── CsvReadingsToReadingsConverter.java
                         │   ├── entity
                         │   │   ├── CsvReadings.java
                         │   └── helper
                         │       └── CsvHelper.java
                         ├── db
                         │   ├── adapter
                         │   │   └── DBReadingsAdapter.java
                         │   └── repository
                         │       └── ReadingsRepository.java
                         └── xml
                             ├── adapter
                             │   └── XmlReadingsAdapter.java
                             ├── converter
                             │   └── XmlReadingsToReadingsConverter.java
                             ├── entity
                             │   └── XmlReadings.java
                             └── helper
                                 └── XmlHelper.java
```

### Notes


You can find multi-module and mono-module implementations all over the internet. I choose mono because of simplicity.
Thought it has several user interaction implementations like rest and cli (input), and a few
sources like database, CSV or XML files (output), currently it only works with XML files. The other implementations
are just for demonstration purposes.

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
shell:>scan --fileName 2016-readings.csv
```

It will parse a list of light consumption readings list, searching for a 50% higher of the median one, in order to
detect a possible fraud and show a results table at the end:

```bash

| Client              | Month              | Suspicious         | Median   |
 ---------------------------------------------------------------------------
| 583ef6329d89b       | SEPTEMBER          | 162078             | 63849,75 |
```

You will find more information about how to use the application by running:

```bash
shell:>info
```
