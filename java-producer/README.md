# Java Producer (Apache Pulsar)

Java application for publishing messages to Apache Pulsar.

## Table of Contents

1. [Description](#description)  
2. [Technologies](#technologies)  
3. [Configuration](#configuration)  
4. [How to Run](#how-to-run)  
5. [Project Structure](#project-structure)  

---

## Description

This project publishes events to Apache Pulsar using Jakarta EE, MicroProfile, and Pulsar Client.  
The goal is to provide a simple, modular message producer ready to be deployed in Docker environments or Jakarta EE application servers.

## Technologies

- Java 23
- Jakarta EE 10
- MicroProfile 6
- Apache Pulsar Client 4.x
- TomEE 10
- Maven 3.9+

## Configuration

Apache Pulsar configurations must be defined in the `microprofile-config.properties` file.  
You can use environment variables to make adjustments between different environments.

Example:

```properties
# Pulsar service URL
pulsar.service-url=${PULSAR_URL:pulsar://pulsar:6650}

# Topic name
pulsar.topic=${PULSAR_TOPIC:character-topic}
```

## How to Run

1. Build the project:
   ```bash
   mvn clean package
   ```
2. The .jar file will be generated in the target/ directory.
3. Deploy using the command:
   ```bash
   java -jar /target/*.jar
   ```

## Estrutura do Projeto

```
src/main/
├── java/com/medium/danieldiasjava/
│   ├── controller/
│   │   └── CharacterController.java
│   ├── model/entity/dto/
│   │   └── CharacterDto.java
│   ├── producer/
│   │   ├── LoggerProduce.java
│   │   └── PulsarClientProducerCDI.java
│   ├── pulsar/producer/
│   │   └── CharacterProducerEvent.java
│   ├── service/
│   │   ├── impl/
│   │   │   └── CharacterServiceImpl.java
│   │   └── CharacterService.java
│   └── FinalFantasyApplicationApi.java
└── resources/
    ├── META-INF/
    │   └── beans.xml
    └── microprofile-config.properties

```

