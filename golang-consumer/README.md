# Golang Consumer (Apache Pulsar)

Go application for consuming messages from Apache Pulsar.

## Table of Contents

1. [Description](#description)
2. [Technologies](#technologies)
3. [Configuration](#configuration)
4. [How to Run](#how-to-run)
5. [Project Structure](#project-structure)

---

## Description

This project consumes messages from Apache Pulsar, processing events published by the Java producer.

## Technologies

- Go 1.18+
- Apache Pulsar Client Go

## Configuration

Set the environment variables:

```yaml
environment:
  - PULSAR_URL=pulsar://pulsar:6650
  - PULSAR_TOPIC=character-topic
  - PULSAR_SUBSCRIPTION=character-sub
```

## How to Run

To run locally:

```bash
go run app/main.go
```

## Project Structure

```
app/
├── main.go
├── go.mod
├── go.sum
├── .env
├── Dockerfile
├── internal/
│   └── config/
│       └── config.go
└── pkg/
    ├── consumer/
    │   └── character_consumer.go
    ├── model/
    │   └── character.go
    └── pulsar/
        └── pulsar_client.go
```